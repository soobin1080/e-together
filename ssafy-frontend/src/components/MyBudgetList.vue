<template>
  <table width="100%">
    <colgroup>
      <col width="30%" />
      <col width="10%" />
      <col width="10%" />
      <col width="20%" />
      <col width="15%" />
      <col width="15%" />
    </colgroup>
    <tr style="text-align:center" height="50px">
      <th>제목</th>
      <th>인원</th>
      <th>예산</th>
      <th>날짜</th>
      <th>적/부</th>
      <th>다운로드</th>
    </tr>
    <template v-if="this.allBudgets.length==0 || this.allBudgets.length==undefined">
      <tr>
        <td colspan="6" style="text-align:center" height="50px">장보기 내역이 없습니다.</td>
      </tr>
    </template>
    <tr v-for="budget in computedPagingBudgets" :key="budget.created_at" @click="say(budget.budget_title)">
      <td v-html="budget.budget_title"
      @click="show_detail(budget.num)"
      style="text-align:center"></td>
      <td style="text-align:center">{{budget.personnel}}</td>
      <td style="text-align:center">{{budget.budget}}</td>
      <td style="text-align:center">{{dateParsing(budget.date)}}</td>
      <td style="text-align:center">{{budget.fitness}}</td>
      <td><v-btn >pdf로 저장</v-btn></td>
    </tr>
  </table>
</template>

<script>
import FirebaseService from "@/services/FirebaseService";
import http from "../http-common";
export default {
  name: "MyBudgetList",

  components: {},

  props: {
    limits: {
      type: Number,
      default: 6
    },
    loadMore: {
      type: Boolean,
      default: false
    },
    pages: {
      type: Number
    },
    allBudgets: {
      type: Array,
      // required: true
    },
    budgetPerPage : {
      type: Number,
     },
    budgetPerPage: {
      type: Number,
      required: true
    }
  },

  data() {
    return {
      pagingbudgets: [],
    };
  },

  methods: {
    dateParsing(beforeParsing) {
      const t = beforeParsing.indexOf('T')
      const afterParsing = beforeParsing.substring(0, t)
      console.log(afterParsing)
      return afterParsing
    },

    say(mytitle) {
      let myEmail = localStorage.getItem('email')
      http
        .get(`/budget/${myEmail}/${mytitle}`, {
          user_email : myEmail,
          budget_title : mytitle
        },
          this.$store.getters.RequestHeader
        )
          .then (res => {
            console.log(res)
          })
          .catch(err => {
            console.log(err)
          })
      
    }


    // async getMyBudgets() {
    //   this.mybudgets = await FirebaseService.getMyBudgets();
    // }
  },
  computed: {
    computedPagingBudgets: function() {
      console.log('computedPagingBudget')
      console.log(this.allBudgets)
      this.pagingBudgets = []
      let start = (this.pages - 1) * this.budgetPerPage
      let end =  (this.pages - 1) * this.budgetPerPage + this.budgetPerPage;

      if (end > this.allBudgets.length) {
        end = this.allBudgets.length;
      }

      for (let i = start; i < end; i++) {
        this.pagingBudgets.push(this.allBudgets[i]);
      }
      console.log(this.pagingBudgets)
      return this.pagingBudgets;

      // console.log('computedPagingBudget')
      // this.pagingBudgets = [];
      // let tmp = 0;
      // for (let i = this.pages * 5 - 5; i < this.pages * 5; i++) {
      //   console.log("i : " + i);
      //   if (i === this.allLength) {
      //     this.limits = tmp;
      //     console.log(tmp);
      //     break;
      //   } else {
      //     this.pagingBudgets.push(this.pagingList[i]);
      //   }
      //   tmp++;
      // }
      // console.log(this.pagingBudgets);
      // return this.pagingBudgets;
    }
  },
  mounted() {
    console.log(this.pagingList)
  }
};
</script>

<style scoped>
</style>