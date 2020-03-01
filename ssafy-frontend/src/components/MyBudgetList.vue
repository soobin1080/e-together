<template>
<div>
<h5 style="text-align:center">나의 장보기 내역</h5><br>
  <table width="100%">
    <colgroup>
      <col width="35%" />
      <col width="10%" />
      <col width="10%" />
      <col width="20%" />
      <col width="10%" />
      <col width="10%" />
      <col width="10%"/>
     
    </colgroup>
    <tr style="text-align:center" height="35px">
      <th>제목</th>
      <th>인원</th>
      <th>예산</th>
      <th>날짜</th>
      <th>적/부</th>
      <th>수정</th>
      <th>삭제</th>
    </tr>
    <template v-if="this.allBudgets.length==0 || this.allBudgets.length==undefined">
      <tr>
        <td colspan="5" style="text-align:center" height="30px">장보기 내역이 없습니다.</td>
      </tr>
    </template>
    <tr v-for="budget in computedPagingBudgets" :key="budget.created_at">
      <td v-html="budget.budget_title"
      @click="show_detail(budget.budget_num)"
      style="text-align:center; cursor:pointer"></td>
      <td style="text-align:center">{{budget.personnel}} 명</td>
      <td style="text-align:center">{{budget.budget}} 원</td>
      <td style="text-align:center">{{dateParsing(budget.budget_date)}}</td>
      <td style="text-align:center">
        <span v-if="budget.suitability == 0">
          미선택
        </span>
        <span v-else-if="budget.suitability == 1">
          적합
        </span>
        <span v-else-if="budget.suitability == 2">
          부적합
        </span>
      </td>
      <td class="text-center text-dark" style="cursor:pointer">
        <i @click="modifyMove(budget)" class="fas fa-tools"></i>
      </td>

      <td class="text-center">
        <i @click="budgetDelete(budget.budget_num)" class="fas fa-trash-alt" style="color: red; cursor:pointer;"></i>
      </td>
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
      // console.log(afterParsing)
      const realdate=afterParsing.substring(0,4)+"년 "+afterParsing.substring(5,7)+"월 "+(Number(afterParsing.substring(8,11))+1)+"일"
      console.log("realdate: "+realdate)
      return realdate 
    },

    show_detail(budgetNum){
      this.$emit('showdetail', budgetNum)
      console.log("--Child (showdetail) : "+budgetNum)
    },

    budgetDelete(budgetNum) {
      // console.log(budgetNum)
      let conf = confirm('이 예산안을 삭제하시겠습니까?')
      // console.log(conf)
      if (conf) {
        http
          .delete(`/budget/${budgetNum}`, {
            budget_num: budgetNum
          }, this.$store.getters.requestHeader)
          .then( res => {
            this.$emit('getMyBudget')
          })
          .catch( err => {
            console.log(err)
          }) 
      } else {
        return;
      }
    },

    modifyMove(budget) {
       if (sessionStorage.getItem('email') !== budget.user_email) {
         alert('본인만 수정이 가능합니다')
         return
       } else {
         this.$router.push({
          name: 'myBudgetModify', 
          params: {
            myBudgets: budget
            }
          })
       }
    }
  },
  computed: {
    computedPagingBudgets: function() {
      console.log('computedPagingBudget')
      console.log(this.allBudgets)
      // console.log(this.allBudgets)
      this.pagingBudgets = []
      let start = (this.pages - 1) * this.budgetPerPage
      let end =  (this.pages - 1) * this.budgetPerPage + this.budgetPerPage;

      if (end > this.allBudgets.length) {
        end = this.allBudgets.length;
      }

      for (let i = start; i < end; i++) {
        this.pagingBudgets.push(this.allBudgets[i]);
      }
      // console.log(this.pagingBudgets)
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
    //console.log(this.pagingList)
    
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
    padding: 4px;
  }
  th {
  color:dimgray;
  background-color: lightgray;
}
</style>