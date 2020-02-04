<template>
  <div style="width:100%">
    <b-list-group-item style="text-align:center; background-color:lightgrey">
      <strong>장보기 내역</strong>
    </b-list-group-item>

    <b-list-group-item class="product_table" style="height:350px">
      <table style="width:100%; margin:auto">
        <col width="40%" />
        <col width="15%" />
        <col width="30%" />
        <col width="15%" />
        <thead>
          <tr style="font-size:10pt; text-align:center">
            <th>항목</th>
            <th>수량</th>
            <th style="text-align:right">가격</th>
            <th style="text-align:right">삭제</th>
          </tr>
        </thead>
        <tbody>          
           <tr v-for="i in list.length" :key="i">
            <td v-html="list[i-1].pro_name" class="product" style="font-size:11pt"></td>
            <td v-html="list[i-1].quantity" class="quantity" style="text-align:right"></td>
            <td class="pro_price" style="text-align:right;">{{list[i-1].price}} 원</td>
            <td style="float:right"><v-btn small style="float:right" >삭제</v-btn></td>
          </tr>
          
        </tbody>
      </table>
     
      <br />
    </b-list-group-item>


    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>인원</strong>
          </td>
          <td width="80%" style="text-align:right">
            <input
              type="number"
              :step="10"
              id="personnel"
              v-model="personnel"
              @input="triggerEvent"
              style="text-align:right"
              autofocus
            />명
          </td>
        </tr>
      </table>
    </b-list-group-item>
    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>예산</strong>
          </td>
          <td width="80%" style="text-align:right">
            <input
              type="number"
              :step="10000"
              id="budget"
              v-model="budget"
              @input="triggerEvent"
              style="text-align:right"
              autofocus
            />원
          </td>
        </tr>
      </table>
    </b-list-group-item>



    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>차트</strong>
          </td>
          <td width="80%" style="text-align:right">
            <table
              style="radius:2; width:100%; margin:auto; text-align:center; background-color:#d9eeff"
            >
              <col width="80px" />
              <col width="100px" />
              <thead>
                <tr>
                  <th>카테고리</th>
                  <th>비율</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>고기</td>
                  <td></td>
                </tr>
                <tr>
                  <td>야채</td>
                  <td></td>
                </tr>
                <tr>
                  <td>밥/라면</td>
                  <td></td>
                </tr>
                <tr>
                  <td>음료</td>
                  <td></td>
                </tr>
                <tr>
                  <td>과자</td>
                  <td></td>
                </tr>
              </tbody>
            </table>
          </td>
        </tr>
      </table>
    </b-list-group-item>



    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>합계</strong>
          </td>
          <td width="80%" style="text-align:right">{{this.total}}원</td>
        </tr>
      </table>
    </b-list-group-item>
    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>잔액</strong>
          </td>
          <td width="80%" style="text-align:right">{{this.budget-this.total}}원</td>
        </tr>
      </table>
    </b-list-group-item>
    <v-btn block @click.stop="dialog=true">저장하기</v-btn>

    <!-- 제목 지정 modal -->
    <v-dialog v-model="dialog" max-width="290">
      <v-card>
        <v-card-title class="headline">리스트 저장</v-card-title>

        <v-col>
          <v-text-field autocomplete="nope" label="제목을 입력해주세요"></v-text-field>
        </v-col>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="budgetSave(true)">저장</v-btn>
          <v-btn color="green darken-1" text @click="budgetSave(false)">취소</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import EventBus from "../event-bus.js";
export default {
  data(){    
    return{
    personnel: "",
    budget: "",
    quantity: 0,
    price: 0,
    dialog: false,
    total: 0,
    mulprice: 0,

    list: []
    }
  },
  
 created() {
    EventBus.$on("addCart", product=>{
      this.list.push(product);
      this.total_sum(product.price);
    });
  },
  methods: {
    triggerEvent() {
      this.$store.state.personnel = this.personnel;
      this.$store.state.budget = this.budget;
    },    
    stringNumberToInt(stringNumber) {
      //console.log(parseInt(stringNumber.replace(/,/g, "")));
      return parseInt(stringNumber.replace(/,/g, ""));
    },
    budgetSave(bool) {
      if (bool === true) {
        const result = confirm("hello");
        if (result) {
          router.push("/mylist");
        }
      }else{
        $emit('close')
      }
    },
    budgetalert() {
      if (this.total > this.budget) {
        alert("예산을 초과하였습니다!");
      }
    },
    total_sum(val) {
      this.total += val;
      this.budgetalert();
      return this.total;
    },
  },
  mounted() {
    this.personnel = this.$store.state.personnel;
    this.budget = this.$store.state.budget;
    if(this.budget=="" || this.personnel==""){
        alert("인원과 예산을 입력해주세요!");
      }
  },
  computed: {
    
  }
};
</script>
<style>
.product_table {
  overflow-y: scroll;
  overflow-x: hidden;
  width: 100%;
}

.product {
  font-size: 12pt;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 190px;
}
</style>