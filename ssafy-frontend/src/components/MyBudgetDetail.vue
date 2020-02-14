<template>
  <div id="downloadpdf" style>
    <div>
    </div>
    <table width="100%" style="font-size:15px" class="table-dark">
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
      <col width="45%" />
      <col width="15%" />
      <col width="25%" />
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
      <v-btn outlined color="success" class="mr-4" @click="makePDF('downloadpdf')">pdf로 저장</v-btn>
      <v-btn outlined color="error" @click.stop="dialog=true">후기 남기기</v-btn>
    </div>

    <!-- 이미지, 내용 등록 modal -->
    <v-dialog data-html2canvas-ignore="true" v-model="dialog" max-width="600">
      <v-card>
        <v-card-title class="headline">후기 등록</v-card-title>
        <p style="padding-left:30px; color:gray">후기를 등록하시면 회원님의 예산 상세 내역이 함께 게시됩니다.!</p>
        <v-col>
          <div class="container">
            <form class="large-12 medium-12 small-12 cell" enctype="multipart/form-data">
              <label>
                Files
                <input
                  type="file"
                  ref="reviewimage"
                  multiple
                  v-on:change="handleFilesUploads()"
                />
              </label>
              <!-- <button v-on:click="submitFiles()">Submit</button> -->
            </form>
          </div>

          <v-textarea
            v-model="content"
            background-color="amber lighten-4"
            color="orange orange-darken-4"
            outlined
            shaped
            placeholder="후기를 작성해주세요!"
          ></v-textarea>
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
export default {
  name: "MyBudgetDetail",
  props: {
    budgetInfo: {
      type: Object
    },
    budgetList: {
      type: Array
    }
    // title :{
    //   type : String
    // }
  },

  data() {
    return {
      // budgetDetail: [],
      detail: {},
      total: 0,
      budgetlength: 0,
      dialog: false,
      content: "",
      propTitle: "mypdf",
      likeClass: "far fa-thumbs-up",
      dislikeClass: "far fa-thumbs-down",
      files: {},
      image: ""
    };
  },

  methods: {
    handleFilesUploads() {
      console.log(this.$refs);
      this.image = this.$refs.reviewimage.files;
      console.log(this.image);
    },

    dateParsing(beforeParsing) {
      const t = beforeParsing.indexOf("T");
      const afterParsing = beforeParsing.substring(0, t);
      console.log(afterParsing);
      return afterParsing;
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

        // /*
        //   Initialize the form data
        // */
        // const formData = new FormData();

        // /*
        //   Iteate over any file sent over appending the files
        //   to the form data.
        // */
        // for (var i = 0; i < this.image.length; i++) {
        //   let file = this.image[i];
        //   console.log(file);
        //   formData.append("files", file);
        // }
        // console.log(formData);
        // console.log(formData._boundary);

        // http
        // .post('review', formData,{
        //   headers:{
        //     'Content-type' : 'multipart/form-data'
        //   }
        // }).then((res)=>{
        //   console.log(res);
        // }).catch((err) => {
        //   console.log(err);
        // });

        this.imageupload();

        http
          .post("/review", {
            budget_num: this.budgetInfo.budget_num,
            review_content: this.content
          })
          .then(response => {
            console.log("content전송!");
            console.log(response);
            // this.result = response.;
          })
          .catch(ex => {
            console.warn("ERROR! :", ex);
          });
        this.$router.push("/review");
      } else {
        this.dialog = false;
      }
    },

    imageupload() {
      /*
          Initialize the form data
        */
      const formData = new FormData();

      /*
          Iteate over any file sent over appending the files
          to the form data.
        */
      for (var i = 0; i < this.image.length; i++) {
        let file = this.image[i];
        console.log(file);
        formData.append("files", file);
      }
      console.log(formData);
      for (var key of formData.keys()) {
        console.log("key");
  console.log(key);

}

for (var value of formData.values()) {
console.log("value");
  console.log(value);

}

      http
        .post(
          "/review/upload",
          {
            files:formData,
            budget_num: this.budgetInfo.budget_num
          },
          {
            headers: {
              "Content-Type": "multipart/form-data"
            }
          }
        )
        .then(response => {
          console.log("image upload response");
          console.log(response);
          // this.result = response.;
        })
        .catch(ex => {
          console.warn("ERROR! :", ex);
        });
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
      // let that = this;
      // let pdf = new jsPDF("p", "mm", "a4");
      // let canvas = pdf.canvas;
      // const pageWidth = 210; //캔버스 너비 mm
      // const pageHeight = 295; //캔버스 높이 mm
      // canvas.width = pageWidth;
      // let ele = document.getElementById(selector);
      // let width = ele.offsetWidth; // 셀렉트한 요소의 px 너비
      // let height = ele.offsetHeight; // 셀렉트한 요소의 px 높이
      // let imgHeight = (pageWidth * height) / width; // 이미지 높이값 px to mm 변환

      // console.log("뭐냐"+selector);

      // let ele = document.querySelector('body');

      // if (!ele) {
      //   console.warn(selector + " is not exist.");
      //   return false;
      // }
      // console.log(canvas);

      //   var canvasElement = document.createElement("canvas");
      // html2canvas(ele, { canvaspdf:canvas }).then(function(canvaspdf) {
      //   ele.appendChild(canvaspdf);
      //   const img = canvaspdf.toDataURL("image/jpeg", 1.0);
      //   pdf.addImage(img, "jpeg", 300,1000, pageWidth, imgHeight);
      //   pdf.save(that.propTitle.toLowerCase() + ".pdf");
      // });

      html2canvas(document.getElementById(selector), {
        onrendered: function(canvas) {
          var imgData = canvas.toDataURL("image/png");
          console.log("Report Image URL: " + imgData);
          var doc = new jsPDF("p", "mm", [297, 210]); //210mm wide and 297mm high
          doc.addImage(imgData, "PNG", 10, 10);
          doc.save(that.propTitle.toLowerCase() + ".pdf");
        }
      });
    }
  },

  mount() {},

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
  },
  created() {
    // this.getMyBudgetDetail(this.budgetDetail);
    // this.total_sum();
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