<template>
  <div>
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
          {{detail.fitness}}
          <div>
            <v-btn class="ma-2" text icon color="blue lighten-2">
              <v-icon>mdi-thumb-up</v-icon>
            </v-btn>

            <v-btn class="ma-2" text icon color="red lighten-2">
              <v-icon>mdi-thumb-down</v-icon>
            </v-btn>
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
    <div style="text-align:center">
      <v-btn outlined color="success">pdf로 저장</v-btn>
      <v-btn outlined color="error" @click.stop="dialog=true">후기 남기기</v-btn>
    </div>


     <!-- 이미지, 내용 등록 modal -->
    <v-dialog v-model="dialog" max-width="290">
      <v-card>
        <v-card-title class="headline">후기 등록</v-card-title>

        <v-col>
          <v-text-field label="이미지를 등록해주세요" v-model="img"></v-text-field>
          <v-text-field autocomplete="nope" label="내용을 입력해주세요" v-model="content"></v-text-field>
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
      img:""
    };
  },

  methods: {
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
     if (bool === true) {
        if (this.content == "") {
          alert("내용을 입력해주세요.");
          return;
        }       
        http
          .post("/review", {
            user_email: localStorage.getItem('email'),
            budget_title: this.title,
            img: this.img,
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
</style>