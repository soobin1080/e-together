<template>
  <div id="downloadpdf">
    <table width="100%" style="font-size:15px">
      <tr style="text-align:center;">
        <th>제목</th>
        <td v-html="computedBudgetDetail.budget_title" style="text-align:center"></td>

        <th>인원</th>
        <td style="text-align:center">{{detail.personnel}} 명</td>

        <th>예산</th>
        <td style="text-align:center">{{detail.budget}} 원</td>

        <th>날짜</th>
        <td style="text-align:center">{{dateParsing(detail.date)}}</td>

        <th>적/부</th>
        <td style="text-align:center">
         
          <div>
            <v-btn class="ma-2" text icon color="blue lighten-2" @click="suitable(1)">
              <v-icon small>mdi-thumb-up</v-icon>
            </v-btn>

            <v-btn class="ma-2" text icon color="red lighten-2" @click="suitable(2)">
              <v-icon small>mdi-thumb-down</v-icon>
            </v-btn>
             {{detail.fitness}}
          </div>
        
          
        </td>
      </tr>
    </table>

    <table style="width:100%; margin:auto; text-align:center">
      <col width="45%" />
      <col width="15%" />
      <col width="25%" />
      <thead >
        <tr >
          <th  style="background-color: #fffeaa; color:#3d2300">항목</th>
          <th  style="background-color: #fffeaa; color:#3d2300">수량</th>
          <th  style="background-color: #fffeaa; color:#3d2300">가격</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="i in detail.budgetlist.length" :key="i" >
          <td v-html="detail.budgetlist[i-1].pro_name"></td>

          <td v-html="detail.budgetlist[i-1].quantity"></td>

          <td class="pro_price">{{detail.budgetlist[i-1].price}} 원</td>
        </tr>

        <tr >
          <th colspan="2">합계</th>
         <td>{{this.total}} 원</td>
        </tr>
          <tr >
          <th colspan="2">잔액</th>
         <td>{{this.detail.budget-this.total}} 원</td>
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

        <v-col>
          <!-- <v-text-field label="이미지를 등록해주세요" v-model="img"></v-text-field> -->
          <div v-if="!image">
              <h6 style="color:darkblue; font-weight:bold">이미지를 등록해주세요!</h6>
              <input type="file" @change="onFileChange">
            </div>
            <div v-else>
              <img :src="image" />
              <button @click="removeImage">Remove image</button>
            </div>
            <v-divider></v-divider>

            <v-textarea v-model="content" background-color="amber lighten-4"
                color="orange orange-darken-4"
                outlined shaped placeholder="후기를 작성해주세요!"></v-textarea>
         
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
import html2canvas from 'html2canvas'
import jsPDF from 'jspdf'
export default {
  name: "MyBudgetDetail",
  props: {
    budgetDetail: {
      type: Array
    },
    title :{ 
      type : String
    }
  },

  data() {
    return {
      // budgetDetail: [],
      detail: {},
      total: 0,
      budgetlength: 0,
      dialog : false,
      content :"",
      image:"",  
      propTitle:'mypdf'    
    };
  },

  methods: {
    onFileChange(e) {
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length)
        return;
      this.createImage(files[0]);
    },
    createImage(file) {
      var image = new Image();
      var reader = new FileReader();
      var vm = this;

      reader.onload = (e) => {
        vm.image = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    removeImage: function (e) {
      this.image = '';
    },
    getMyBudgetDetail(budgetDetail) {
      let myEmail = localStorage.getItem("email");
      console.log("!myEmail :" + myEmail);
      let mytitle = budgetDetail.budget_title;
      console.log("!mytitle :" + mytitle);
      http
        .get(
          "/budget/" + myEmail + "/" + mytitle,
          {
            user_email: myEmail,
            budget_title: mytitle
          },
          this.$store.getters.RequestHeader
        )
        .then(res => {
          console.log("getMyBudgetDetail");
          console.log(res)
          this.detail = res.data;
          console.log("데이터 : " + this.detail);
          console.log("항목 : " + this.detail.budgetlist);
          console.log("항목 : " + this.detail.budgetlist.length);
          this.budgetlength=this.detail.budgetlist.length;
          for (let index = 0; index < this.budgetlength; index++) {
         this.total += Number(this.detail.budgetlist[index].price);          
       }  
      return this.total;
        })
        .catch(err => {
          console.log(err);
        });
    },
    dateParsing(beforeParsing) {
      const t = beforeParsing.indexOf("T");
      const afterParsing = beforeParsing.substring(0, t);
      console.log(afterParsing);
      return afterParsing;
    },
      total_sum() {  
       console.log("budgetList 길이!:"+ this.budgetlength); 
       for (let index = 0; index < this.detail.budgetlist; index++) {
         this.total += detail.budgetlist[index].price;          
       }  
      return this.total;
    },
    writeReview(bool){
      console.log("이미지는: "+this.image);
     if (bool === true) {
        if (this.content == "") {
          alert("내용을 입력해주세요.");
          return;
        }       
        http
          .post("/review", {
            user_email: localStorage.getItem('email'),
            budget_title: this.title,
            img: this.image,
            content: this.content            
          }, this.$store.getters.requestHeader)
          .then(response => {
            console.log(response)
            // this.result = response.;
          })
          .catch(ex => {
            console.warn("ERROR! :", ex);
          });
        this.$router.push("/review");
      } else {        
        this.dialog=false;
      }
    },
    suitable(num){
      let myEmail = localStorage.getItem("email");
      console.log("!myEmail :" + myEmail);
      let mytitle = this.computedBudgetDetail.budget_title;
      console.log("!mytitle :" + mytitle);
      if(num==1){
        this.detail.fitness="적합";
      }else if(num==2){
        this.detail.fitness="부적합";
      }
      http
      .post( "/budget/" + myEmail + "/" + mytitle,
          {
            user_email: myEmail,
            budget_title: mytitle,
            fitness:this.detail.fitness
          },this.$store.getters.requestHeader)
          .then(response => {
            console.log(response)
            // this.result = response.;
          })
          .catch(ex => {
            console.warn("ERROR! :", ex);
          });
        this.$router.push("/mybudget");
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
                var imgData = canvas.toDataURL('image/png');
                console.log('Report Image URL: '+imgData);
                var doc = new jsPDF('p', 'mm', [297, 210]); //210mm wide and 297mm high
                doc.addImage(imgData, 'PNG', 10, 10);
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
    computedBudgetDetail: function() {
      console.log('computedBudgetDetail')
      console.log(this.budgetDetail)
      this.getMyBudgetDetail(this.budgetDetail)
      return this.budgetDetail
    }
  },
  created() {
    this.getMyBudgetDetail(budgetDetail);
    this.total_sum();
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
  color:dimgray;
  background-color: lightgray;
}
img {
  width: 30%;
  margin: auto;
  display: block;
  margin-bottom: 10px;
}
</style>