<template>
<div>
<h5 style="text-align:center">목록</h5><br>
  <table width="100%">
    <colgroup>
      <col width="30%" />
      <col width="15%" />
      <col width="15%" />
      <col width="25%" />
      <col width="15%" />
     
    </colgroup>
    <tr style="text-align:center" height="35px">
      <th>제목</th>
      <th>인원</th>
      <th>예산</th>
      <th>날짜</th>
      <th>적/부</th>
     
    </tr>
    <template v-if="this.allBudgets.length==0 || this.allBudgets.length==undefined">
      <tr>
        <td colspan="5" style="text-align:center" height="30px">장보기 내역이 없습니다.</td>
      </tr>
    </template>
    <tr v-for="budget in computedPagingBudgets" :key="budget.created_at">
      <td v-html="budget.budget_title"
      @click="show_detail(budget.budget_title)"
      style="text-align:center"></td>
      <td style="text-align:center">{{budget.personnel}} 명</td>
      <td style="text-align:center">{{budget.budget}} 원</td>
      <td style="text-align:center">{{dateParsing(budget.date)}}</td>
      <td style="text-align:center">{{budget.fitness}}  <div>
      <v-btn class="ma-2" text icon color="blue lighten-2">
        <v-icon small>mdi-thumb-up</v-icon>
      </v-btn>

      <v-btn class="ma-2" text icon color="red lighten-2">
        <v-icon small>mdi-thumb-down</v-icon>
      </v-btn>
    </div></td>
     
    </tr>
  </table>
</div>
</template>

<script>
import FirebaseService from "@/services/FirebaseService";
import http from "../http-common";
export default {
  name: "MyBudgetList",

  components: {},

  props: {    
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

    show_detail(title){
      this.$emit('showdetail', title)
      console.log("--Child (showdetail) : "+title)
    }
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
table {
    width: 100%;
    border-top: 1px solid lightgray;
    border-collapse: collapse;
  }
  th, td {
    font-size:15px;
    border-bottom: 1px solid lightgray;
    padding: 1px;
  }
  th {
  color:dimgray;
  background-color: lightgray;
}
</style>