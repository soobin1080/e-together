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

    <div class="main" fluid>
      <div style="width:60%; height: 100%;" class="mx-auto my-5 flat" data-html2canvas-ignore="true">
        <MyBudgetList 
          :allBudgets="allBudgets"
          :budgetPerPage="budgetPerPage"
          :pages="pages"
          v-on:showdetail="showdetail"
          @getMyBudget="getMyBudgets"
        ></MyBudgetList>
      </div>

      <div class="text-center" fluid style="padding-bottom:40px" data-html2canvas-ignore="true">
        <v-pagination v-model="pages" :length="pagingLength" total-visible="9"></v-pagination>
      </div>

      <div class="mx-auto my-5 flat" style="width:60%" v-if="showflag==true">
        <MyBudgetDetail
          id="downloadpdf"
          :budgetList="budgetList"
          :budgetInfo="budgetInfo"
          @renewBudgetList="getMyBudgets"
          @showdetail="showdetail"
        ></MyBudgetDetail>
      </div>
        <!-- <v-btn outlined color="success" class="mr-4" @click="makePDF()">pdf로 저장</v-btn> -->
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
import html2canvas from "html2canvas";
import jsPDF from "jspdf";
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
    budgetList: [],
    budgetInfo: Object,
    budgetDetail: Object,
    showflag: false
  }),
  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    },

    getMyBudgets() {
      console.log("getMyBudget");
      let myEmail = sessionStorage.getItem("email");
      // console.log(myEmail)
      http
        .get(
          "/budget/" + myEmail,
          {
            user_email: myEmail
          },
          this.$store.getters.RequestHeader
        )
        .then(res => {
          console.log("getMyBudget");
          this.allBudgets = res.data;
          console.log(res.data);
          this.allLength = res.data.length;
          this.pagingList = this.allBudgets;

          this.allLength = this.pagingList.length;
          if (this.allLength % this.budgetPerPage === 0) {
            this.pagingLength = parseInt(this.allLength / this.budgetPerPage);
          } else {
            this.pagingLength =
              parseInt(this.allLength / this.budgetPerPage) + 1;
          }
          // this.pages = 1;
          // console.log(res)
          // return res.data
        })
        .catch(err => {
          console.log(err);
        });
    },
    showdetail(budgetNum) {
      this.showflag = true;
      if (this.showflag === true) {
        // console.log("--Parent (showdetail) : " + budgetNum);
        http
          .get(
            `/budget/detail/${budgetNum}`,
            {
              budget_num: budgetNum
            },
            this.$store.getters.RequestHeader
          )
          .then(res => {
            // console.log(res)
            this.budgetInfo = res.data.budgetinfo;
            this.budgetList = res.data.budgetlist;
            // console.log(this.budgetInfo)
            // console.log(this.budgetList)
          });
        // this.budgetDetail.budget_title = title;
        // this.title = title
        // // this.budgetDetail =
      }
      // this.showflag=false;
    },
    makePDF() {
      // console.log(document.getElementById('downloadpdf'));
      // window.open(document.getElementById('downloadpdf'),"width=400,height=500");
      // console.log(document.querySelector("#downloadpdf"));
      // html2canvas(document.querySelector("#downloadpdf")).then(function(canvas) {
      //   var doc = new jsPDF("p", "mm", "a4"); //jspdf객체 생성
      //   var imgData = canvas.toDataURL("image/png"); //캔버스를 이미지로 변환
      //   doc.addImage(imgData, "PNG", 0, 0); //이미지를 기반으로 pdf생성
      //   doc.save("sample-file.pdf"); //pdf저장
      // });

      // window.html2canvas = html2canvas //Vue.js 특성상 window 객체에 직접 할당해야한다.
			// let that = this
			// let pdf = new jsPDF('p', 'mm', 'a4')
			// let canvas = pdf.canvas
			// const pageWidth = 210 //캔버스 너비 mm
			// const pageHeight = 295 //캔버스 높이 mm
			// canvas.width = pageWidth
			// let ele = document.querySelector('body')

			// let width = ele.offsetWidth // 셀렉트한 요소의 px 너비
			// let height = ele.offsetHeight // 셀렉트한 요소의 px 높이
			// let imgHeight = pageWidth * height/width // 이미지 높이값 px to mm 변환

      // console.log("이것은 ele : "+ele);

      //  html2canvas(document.querySelector('body')).then(function(canvas) {
      //   var doc = new jsPDF("p", "mm", "a4"); //jspdf객체 생성
      //   var imgData = canvas.toDataURL("image/png"); //캔버스를 이미지로 변환
      //   doc.addImage(imgData, "PNG", 0, 0,pageWidth, imgHeight, undefined, 'slow'); //이미지를 기반으로 pdf생성
      //   doc.save("sample-file.pdf"); //pdf저장
      // });

			// html2canvas(ele, {
			// 	onrendered: function(canvas) {
			// 		let position = 0
			// 		const imgData = canvas.toDataURL('image/png')
			// 		pdf.addImage(imgData, 'png', 0, position, pageWidth, imgHeight, undefined, 'slow')
			// 		pdf.save(that.propTitle.toLowerCase() +'.pdf')
			// 	},
			// });	
    }
  },
  computed: {
    // mountedBudget() {
    //   // console.log("mountedBudget");
    //   this.allBudgets = this.getMyBudgets();
    //   this.allLength = this.allBudgets.length;
    //   return this.getMyBudgets();
    // }
  },
  mounted() {
    this.getMyBudgets();
  }
};
</script>

<style scoped>
.main {
  padding-top: 60px;
  padding-bottom: 80px;
}
</style>