<template>
  <div>
    <v-img :src="getImgUrl('budgetlist.jpg')" aspect-ratio="5.5">
      <v-layout align-center justify-center row fill-height>
        <v-flex text-xs-center>
          <span class="text-shadow display-2 font-weight-light">
            <slot name="text" />
            <div
              class="text-center text-white"
              style="line-height:1.2em; font-size:2.5em;"
              slot="text"
              v-resize-text
            >My Budget</div>
          </span>
        </v-flex>
      </v-layout>
    </v-img>

    <div class="main">
      <div style="width:60%; height: 100%;" class="mx-auto my-5 flat">
        <MyBudgetList
          :allBudgets="allBudgets"
          :budgetPerPage="budgetPerPage"
          :pages="pages"
          v-on:showdetail="showdetail"
        ></MyBudgetList>
      </div>
     

      <div class="text-center">
        <v-pagination v-model="pages" :length="pagingLength" total-visible="9"></v-pagination>
      </div>
    </div>
    <div class="mx-auto my-5 flat" style="width:60%" v-if="showflag==true">
     <MyBudgetDetail
      :budgetDetail="budgetDetail" :title="title"></MyBudgetDetail>
    </div>

  </div>  
</template>

<script>
import FirebaseService from "@/services/FirebaseService";
import ImgBanner from "../components/ImgBanner";
import MyBudgetList from "../components/MyBudgetList";
import MyBudgetDetail from "../components/MyBudgetDetail";
import ResizeText from "vue-resize-text";
import http from "../http-common";
export default {
  name: "MyBudgetPage",

  components: {
    ImgBanner,
    MyBudgetList,
    MyBudgetDetail
  },
  directives: {
    ResizeText
  },
  data: () => ({
    pages: 1,
    allBudgets: [],
    pagingList: [],
    pagingLength: 0,
    allLength: 0,
    budgetPerPage: 5,
    budgetDetail: [],
    showflag : false
  }),
  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    },

    getMyBudgets() {
      let myEmail = localStorage.getItem("email");
      http
        .get(
          "/budget/"+myEmail,
          {
            user_email: myEmail
          },
          this.$store.getters.RequestHeader
        )
        .then(res => {
          console.log("getMyBudget");
          this.allBudgets = res.data;
          this.allLength = res.data.length;
          this.pagingList = this.allBudgets;

          this.allLength = this.pagingList.length;
          if (this.allLength % this.budgetPerPage === 0) {
            this.pagingLength = parseInt(this.allLength / this.budgetPerPage);
          } else {
            this.pagingLength =
              parseInt(this.allLength / this.budgetPerPage) + 1;
          }
          this.pages = 1;
          // console.log(res)
          // return res.data
        })
        .catch(err => {
          console.log(err);
        });
    },
    showdetail(title) {
      this.budgetDetail = []
      this.showflag=true;
      if(this.showflag==true){
      console.log("--Parent (showdetail) : " + title);
      this.budgetDetail.budget_title = title;
      this.title = title
      // this.budgetDetail =  
      }
      // this.showflag=false;      
    }
  },
  computed: {
    mountedBudget() {
      console.log("mountedBudget");
      this.allBudgets = this.getMyBudgets();
      this.allLength = this.allBudgets.length;
      return this.getMyBudgets();
    }
  },
  mounted() {
    this.getMyBudgets();   
  }
};
</script>

<style scoped>
.main {
  padding-top: 80px;
  padding-bottom: 80px;
}
</style>