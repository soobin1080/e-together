<template>
  <div style="width:100%">
    <b-list-group-item style="text-align:center; background-color:lightgrey">
      <strong>장보기 내역</strong>
    </b-list-group-item>
    <b-list-group-item class="product_table" style="height:350px">
      <table style="width:100%; margin:auto">
        <col width="50%" />
        <col width="15%" />
        <col width="35%" />
        <thead>
          <tr style="text-align:center">
            <th>항목</th>
            <th>수량</th>
            <th>가격</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="product" style="font-size:9pt">{{this.$store.state.pro_name}}</td>
            <td class="quantity" style="text-align:right">{{this.$store.state.quantity}}</td>
            <td class="pro_price" style="text-align:right">{{mul}}원</td>
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
            <input type="number" :step="10" id="people" v-model="people" @input="triggerEvent"
              style="text-align:right" />명
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
            <input type="number" :step="10000" id="money" v-model="money" @input="triggerEvent"
              style="text-align:right" />원
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
            <table style="radius:2; width:100%; margin:auto; text-align:center; background-color:#d9eeff">
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
          <td width="80%" style="text-align:right">{{total}}원</td>
        </tr>
      </table>
    </b-list-group-item>
    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>잔액</strong>
          </td>
          <td width="80%" style="text-align:right">원</td>
        </tr>
      </table>
    </b-list-group-item>
    <v-btn block @click.stop="dialog=true">저장하기</v-btn>

    <!-- 제목 지정 modal -->
    <v-dialog v-model="dialog" max-width="290">
      <v-card>
        <v-card-title class="headline">제목</v-card-title>

        <v-col>
          <v-text-field autocomplete="nope" label="제목"></v-text-field>
        </v-col>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="green darken-1" text @click="budgetSave(true)">
            저장
          </v-btn>

          <v-btn color="green darken-1" text @click="budgetSave(false)">
            취소
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
  export default {
    data: () => ({
      total: "",
      people: "",
      money: "",
      quantity: 0,
      price: 0,
      dialog: false,
    }),
    created() {
      this.people = this.people;
      this.money = this.money;
    },

      methods: {
        triggerEvent() {
          this.$store.state.people = this.people;
          this.$store.state.money = this.money;
        },
        cal() {
          this.total += Number(this.mul);
        },
        list_submit() {
          // DB에 저장(Sub III때 구현)
        },
        stringNumberToInt(stringNumber) {
          console.log(parseInt(stringNumber.replace(/,/g, "")));
          return parseInt(stringNumber.replace(/,/g, ""));
        },
        budgetSave(bool) {
          if (bool === true) {

            const result = confirm("hello")
            if (result) {
              router.push("/mylist")
            }
          }
        }
      },
    mounted() {
      this.people = this.$store.state.people;
      this.money = this.$store.state.money;
    },
    computed: {
      mul: function () {
        this.quantity = this.$store.state.quantity;
        this.price = this.stringNumberToInt(this.$store.state.price);
        return this.quantity * this.price;
      },
    }
  };
</script>
<style>
  .product {
    display: inline-block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .product_table {
    overflow-y: scroll;
    overflow-x: hidden;
    width: 100%;
  }
</style>