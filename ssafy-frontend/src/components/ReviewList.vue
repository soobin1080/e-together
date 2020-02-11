<template>
  <table width="100%">
    <colgroup>
      <col width="20%" />
      <col width="10%" />
      <col width="10%" />
      <col width="20%" />
      <col width="20%" />
       <col width="10%" />
        <col width="10%" />
    </colgroup>
    <tr style="text-align:center" height="50px">
      <th>제목</th>
      <th>인원</th>
      <th>예산</th>
      <th>작성자</th>
      <th>날짜</th>
      <th>적/부</th>
      <th>좋아요</th>
    </tr>
    <template v-if="this.allLength==0 || this.allLength==undefined">
      <tr>
        <td colspan="7" style="text-align:center" height="50px">장보기 내역이 없습니다.</td>
      </tr>
    </template>
    <tr v-for="budget in computedPagingBudgets" :key="budget.created_at">
      <td v-html="budget.title"
      @click="show_detail(budget.num)"
      style="text-align:center"></td>
      <td v-html="budget.people"></td>
      <td v-html="budget.money"></td>
      <td v-html="buget.nickname"></td>
      <td v-html="budget.date"></td>
      <td v-html="budget.fitness"></td>
       <td v-html="budget.likecount"></td>
    </tr>
  </table>
</template>

<script>
import FirebaseService from "@/services/FirebaseService";
export default {
  name: "ReviewList",

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
    pagingList: {
      type: Array,
      required: true
    },
    allLength: {
      type: Number,
      required: true
    },
    allReviews:{
      type: Array
    }
  },

  data() {
    return {
      pagingbudgets: []
    };
  },

  methods: {
    // async getMyBudgets() {
    //   this.mybudgets = await FirebaseService.getMyBudgets();
    // }
    show_detail(num) {
      
    }
  },
  computed: {
    computedPagingBudgets: function() {
      this.pagingBudgets = [];
      let tmp = 0;
      for (let i = this.pages * 5 - 5; i < this.pages * 5; i++) {
        console.log("i : " + i);
        if (i === this.allLength) {
          this.limits = tmp;
          console.log(tmp);
          break;
        } else {
          this.pagingBudgets.push(this.pagingList[i]);
        }
        tmp++;
      }
      console.log(this.pagingBudgets);
      return this.pagingBudgets;
    }
  },
  mounted() {}
};
</script>

<style scoped>
</style>