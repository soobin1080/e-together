<template>
  <div id="downloadpdf" style>
    <div></div>
    <table width="100%" style="font-size:15px" class="table-white">
      <tr style="text-align:center;">
        <th>제목</th>
        <td v-html="computedBudgetInfo.budget_title" style="text-align:center"></td>
        <th>인원</th>
        <td style="text-align:center">{{computedBudgetInfo.personnel}} 명</td>
        <th>예산</th>
        <td style="text-align:center">{{computedBudgetInfo.budget}} 원</td>
        <th>날짜</th>
        <td style="text-align:center">{{dateParsing(computedBudgetInfo.budget_date)}}</td>

        <th>적/부</th>
        <td style="text-align:center">
          <!-- 적 / 부 -->
          <div>
            <i
              :class="likeClass"
              style="color:blue; margin-right: 5px;"
              @click="changeLikeStatus(1)"
            ></i>
            <!-- <i class="fas fa-thumbs-up"></i> -->
            <i :class="dislikeClass" style="color:red" @click="changeLikeStatus(2)"></i>
            <!-- <i class="fas fa-thumbs-down"></i> -->
          </div>
        </td>
      </tr>
    </table>

    <table style="width:100%; margin:auto; text-align:center">
      <col width="55%" />
      <col width="15%" />
      <col width="30%" />
      <thead>
        <tr>
          <th style="background-color: #fffeaa; color:#3d2300">항목</th>
          <th style="background-color: #fffeaa; color:#3d2300">수량</th>
          <th style="background-color: #fffeaa; color:#3d2300">가격</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="i in computedBudgetList.length" :key="i">
          <td v-html="computedBudgetList[i-1].pro_name"></td>

          <td v-html="computedBudgetList[i-1].quantity"></td>

          <td class="pro_price">{{computedBudgetList[i-1].price}} 원</td>
        </tr>

        <tr>
          <th colspan="2">합계</th>
          <td>{{this.total}} 원</td>
        </tr>
        <tr>
          <th colspan="2">잔액</th>
          <td>{{computedBudgetInfo.budget-this.total}} 원</td>
        </tr>
      </tbody>
    </table>
    <br />
    <div data-html2canvas-ignore="true" style="text-align:center">
      <a
        href="javascript:;"
        @click="kakaotalklink"
        id="kakao-link-btn"
        style="width:30px; height:auto"
      >
        <!-- 버튼이 생기는 부분, id는 맘대로 쓰시되 아래 js 코드도 동일하게 적용해주셔야 합니다. -->
        <img
          src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"
          style="width:30px; height:auto"
        />
        <!-- 톡 이미지 부분이고, 전 kakaolink_btn_small.png로 불러왔습니다.   -->
      </a>

      <v-btn
        outlined
        color="success"
        class="mr-4"
        id="kakao-link-btn"
        @click="makePDF('downloadpdf')"
      >pdf로 저장</v-btn>
      <v-btn outlined color="error" @click.stop="dialog=true">후기 남기기</v-btn>
    </div>

    <!-- 이미지, 내용 등록 modal -->
    <v-dialog data-html2canvas-ignore="true" v-model="dialog" max-width="600">
      <v-card>
        <v-card-title class="headline">후기 등록</v-card-title>
        <p style="padding-left:30px; color:gray">후기를 등록하시면 회원님의 예산 상세 내역이 함께 게시됩니다.!</p>
        <v-col>
          <div class="container">
            <div class="large-12 medium-12 small-12 cell">
              <label>
                Files
                <input type="file" id="files" ref="files" v-on:change="handleFilesUploads()" />
              </label>
              <v-textarea
                v-model="content"
                background-color="amber lighten-4"
                color="orange orange-darken-4"
                outlined
                shaped
                placeholder="후기를 작성해주세요!"
              ></v-textarea>
            </div>
          </div>
        </v-col>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="writeReview(true)">저장</v-btn>
          <v-btn color="green darken-1" text @click="writeReview(false)">취소</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import http from "../http-common";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";
import Kakao from "../services/KakaotalkService";
export default {
  name: "MyBudgetDetail",
  props: {
    budgetInfo: {
      type: Object
    },
    budgetList: {
      type: Array
    }
  },

  data() {
    return {
      detail: {},
      total: 0,
      budgetlength: 0,
      dialog: false,
      content: "",
      propTitle: "mypdf",
      likeClass: "far fa-thumbs-up",
      dislikeClass: "far fa-thumbs-down",
      files: ""
    };
  },

  methods: {
    kakaotalklink() {
      //<![CDATA[
      // // 사용할 앱의 JavaScript 키를 설정해 주세요.
      Kakao.init("918cb2cf36b91b4f11f0a63002dc2154");
      // // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
      Kakao.Link.createDefaultButton({
        container: "#kakao-link-btn", // 컨테이너는 아까 위에 버튼이 쓰여진 부분 id
        objectType: "feed",
        content: {
          // 여기부터 실제 내용이 들어갑니다.
          title: this.computedBudgetInfo.budget_title, // 본문 제목
          description: this.dateParsing(this.computedBudgetInfo.budget_date), // 본문 바로 아래 들어가는 영역?
          imageUrl:
            "../etogether.png", // 이미지
          link: {
            mobileWebUrl: "https://developers.kakao.com",
            webUrl: "https://developers.kakao.com"
          }
        },
        // social: {
        //   /* 공유하면 소셜 정보도 같이 줄 수 있는데, 이 부분은 기반 서비스마다 적용이 쉬울수도 어려울 수도 있을듯 합니다. 전 연구해보고 안되면 제거할 예정 (망할 google  blogger...) */
        //   likeCount: 286,
        //   commentCount: 45,
        //   sharedCount: 845
        // },
        buttons: [
          {
            title: "e투계더로 이동",
            link: {
              mobileWebUrl: "https://developers.kakao.com",
              webUrl: "https://developers.kakao.com"
            }
          },
          
        ]
      });
    },
    handleFilesUploads() {
      this.files = this.$refs.files.files;
    },

    dateParsing(beforeParsing) {      
      const t = beforeParsing.indexOf('T')
      const afterParsing = beforeParsing.substring(0, t)      
      // console.log(afterParsing)
      const realdate=afterParsing.substring(0,4)+"년 "+afterParsing.substring(5,7)+"월 "+(Number(afterParsing.substring(8,11))+1)+"일"
      console.log("realdate: "+realdate)
      return realdate 
    },
    total_sum() {
      //  console.log("budgetList 길이!:"+ this.budgetlength);
      for (
        let index = 0;
        index < this.computedBudgetDetail.budgetlist.length;
        index++
      ) {
        this.total += this.computedBudgetDetail.budgetlist[index].price;
      }
      return this.total;
    },
    writeReview(bool) {
      if (bool === true) {
        if (this.content == "") {
          alert("내용을 입력해주세요.");
          return;
        }

        let formData = new FormData();

        for (var i = 0; i < this.files.length; i++) {
          let file = this.files[i];
          formData.append("files", file);
        }
        formData.append("budget_num", this.budgetInfo.budget_num);
        alert("budget_num: " + this.budgetInfo.budget_num);
        formData.append("review_content", this.content);
        alert("review_content: " + this.content);

        http
          .post("/review", formData)
          .then(response => {
            console.log("SUCCESS!!");
            console.log(response);
            // this.result = response.;
          })
          .catch(ex => {
            console.log("FAILURE!!");
          });
        this.$router.push("/review");
      } else {
        this.dialog = false;
      }
    },
    checklikeStatus() {
      let status = this.budgetInfo.suitability;
      // const like = document.querySelector('.fa-thumbs-up')
      // console.log(status)
      // console.log(like.className)
      // const dislike = document.querySelector('.fa-thumbs-down')
      if (status === 0) {
        this.likeClass = "far fa-thumbs-up";
        this.dislikeClass = "far fa-thumbs-down";
      } else if (status === 1) {
        this.likeClass = "fas fa-thumbs-up";
        this.dislikeClass = "far fa-thumbs-down";
      } else {
        this.likeClass = "far fa-thumbs-up";
        this.dislikeClass = "fas fa-thumbs-down";
      }
      // if (status == 0) {
      //   return
      // } else if (status == 1) {
      //   like.className = 'fas fa-thumbs-up'
      //   dislike.className = 'far fa-thumbs-up'
      // } else {
      //   like.className = 'far fa-thumbs-up'
      //   dislike.className = 'fas fa-thumbs-up'
      // }
    },
    changeLikeStatus(num) {
      // console.log(this.computedBudgetInfo.budget_num)
      console.log("changeLikeStatus");
      console.log("num : " + num);
      // console.log("this suit : "+ this.computedBudgetInfo.suitability)
      // console.log("computedBudgetNum : "+ this.computedBudgetInfo.budget_num)
      console.log("budget suit : " + this.budgetInfo.suitability);
      if (this.computedBudgetInfo.suitability === num || num == 0) {
        // console.log("same status")

        return;
      } else {
        http.post(
          `/budget/${this.budgetInfo.budget_num}/${num}`,
          {
            budget_num: this.budgetInfo.budget_num,
            suitability: num
          },
          this.$store.getters.requestHeader
        );
        // if (num === 1) {
        //   this.likeClass = "fas fa-thumbs-up";
        //   this.dislikeClass = "far fa-thumbs-down";
        //   // this.budgetInfo.suitability = 1
        // } else if (num === 2) {
        //   this.likeClass = "far fa-thumbs-up";
        //   this.dislikeClass = "fas fa-thumbs-down";
        //   // this.budgetInfo.suitability = 2
        // }
        this.$emit("renewBudgetList");
        this.$emit("showdetail", this.budgetInfo.budget_num);
      }
    },
    makePDF(selector) {
      // console.log(selector);
      window.html2canvas = html2canvas; //Vue.js 특성상 window 객체에 직접 할당해야한다.
      let that = this;
      let pdf = new jsPDF("p", "mm", "a4");
      let canvas = pdf.canvas;
      const pageWidth = 210; //캔버스 너비 mm
      const pageHeight = 295; //캔버스 높이 mm
      canvas.width = pageWidth;
      let ele = document.getElementById(selector);
      let width = ele.offsetWidth; // 셀렉트한 요소의 px 너비
      let height = ele.offsetHeight; // 셀렉트한 요소의 px 높이
      let imgHeight = (pageWidth * height) / width; // 이미지 높이값 px to mm 변환

      console.log("뭐냐" + selector);

      // let ele = document.querySelector('body');

      if (!ele) {
        console.warn(selector + " is not exist.");
        return false;
      }
      console.log(canvas);

      var canvasElement = document.createElement("canvas");
      html2canvas(ele, { canvaspdf: canvas }).then(function(canvaspdf) {
        ele.appendChild(canvaspdf);
        const img = canvaspdf.toDataURL("image/jpeg", 1.0);
        pdf.addImage(img, "jpeg", 0, 0, pageWidth, imgHeight);
        pdf.save(that.propTitle.toLowerCase() + ".pdf");
      });

      // html2canvas(document.getElementById(selector), {
      //   onrendered: function(canvas) {
      //     var imgData = canvas.toDataURL("image/png");
      //     console.log("Report Image URL: " + imgData);
      //     var doc = new jsPDF("p", "mm", [297, 210]); //210mm wide and 297mm high
      //     doc.addImage(imgData, "PNG", 10, 10);
      //     doc.save(that.propTitle.toLowerCase() + ".pdf");
      //   }
      // });
    }
  },
  computed: {
    convertDate() {
      console.log("convertDate");
      let convertDate = new Date(this.date);
      console.log(convertDate);
      return this.date;
    },
    computedBudgetInfo: function() {
      console.log("computedBudgetInfo");
      console.log(this.budgetInfo);
      console.log(this.budgetInfo.suitability);
      this.checklikeStatus();
      return this.budgetInfo;
      // this.getMyBudgetDetail(this.budgetDetail)
      // console.log(this.budgetDetail.budgetlist)
      // this.total = this.budgetDetail.budgetlist.reduce((total, budget) => total += (budget.price * budget.quantity), 0)
      // return this.budgetDetail
    },
    computedBudgetList: function() {
      console.log("computedBudgetList");
      // console.log(this.bungetList)
      this.total = this.budgetList.reduce(
        (total, budget) => (total += budget.price * budget.quantity),
        0
      );
      console.log(this.total);
      return this.budgetList;
    }
  }
};
</script>

<style scoped>
table {
  width: 100%;
  border-top: 1px solid dimgray;
  border-collapse: collapse;
}
th,
td {
  border-bottom: 1px solid dimgray;
  padding: 5px;
}
th {
  color: dimgray;
  background-color: lightgray;
}
img {
  width: 30%;
  margin: auto;
  display: block;
  margin-bottom: 10px;
}
</style>