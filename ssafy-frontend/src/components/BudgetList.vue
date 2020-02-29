<template>
  <div style="width:100%">
    <b-list-group-item style="background-color:lightgrey">
      <strong style="text-align:center;">장보기 내역</strong>
      <span
        class="myButton"
        @click="changeRecommendBar"
        style="text-align:right; float:right; color:dimgrey"
      >비율 추천</span>
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
              min="10"
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
              min="10000"
              id="budget"
              v-model="budget"
              @input="triggerEvent"
              style="text-align:right"
              autofocus
            />원
            <!-- <v-btn text>예산 추천</v-btn> -->
          </td>
        </tr>
      </table>
    </b-list-group-item>
    <!-- <v-btn block color="bg-warning" @click="changeRecommendBar">비율 추천</v-btn> -->

    <b-list-group-item class="product_table" style="height:500px">
      <table style="width:100%; margin:auto">
        <col width="45%" />
        <col width="15%" />
        <col width="25%" />
        <col width="15%" />
        <thead>
          <tr style="font-size:10pt; text-align:center;">
            <th>항목</th>
            <th>수량</th>
            <th>가격</th>
            <th>삭제</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="i in computedBudgetList.length" :key="i" style="font-size:10pt;">
            <td v-html="computedBudgetList[i-1].pro_name" class="product"></td>
            <!-- <td v-html="list[i-1].quantity" class="quantity" style="text-align:center"></td> -->
            <td class style="text-align:center">
              <input
                class="quantity"
                type="number"
                style="text-align:right; width:50px"
                @change="changeQuantity(computedBudgetList[i-1], i-1, $event)"
                :value="computedBudgetList[i-1].quantity"
                min="1"
              />
            </td>

            <td class="pro_price" style="text-align:center;">{{computedBudgetList[i-1].pro_price}} 원</td>
            <td style="text-align:center;">
              <v-btn text icon color="red" @click="del_pro(i-1)">
                <i class="fas fa-trash-alt"></i>
              </v-btn>
            </td>
          </tr>
        </tbody>
      </table>

      <br />
    </b-list-group-item>

    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>합계</strong>
          </td>
          <td
            width="80%"
            style="text-align:right; font-weight:bold; color:darkblue"
          >{{this.$store.state.mainTotal + this.$store.state.etcTotal}} 원</td>
        </tr>
      </table>
    </b-list-group-item>
    <b-list-group-item>
      <table style="width:100%; margin:auto">
        <tr>
          <td width="20%">
            <strong>잔액</strong>
          </td>
          <td
            width="80%"
            style="text-align:right; font-weight:bold; color:darkred"
          >{{this.budget - (this.$store.state.mainTotal + this.$store.state.etcTotal)}} 원</td>
        </tr>
      </table>
    </b-list-group-item>

    <v-btn block @click.stop="dialog=true">저장하기</v-btn>

    <!-- 제목 지정 modal -->
    <v-dialog v-model="dialog" max-width="290">
      <v-card>
        <v-card-title class="headline">My Budget 저장</v-card-title>

        <v-col>
          <v-text-field autocomplete="nope" label="제목을 입력해주세요" v-model="budgetTitle"></v-text-field>
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
import http from "../http-common";
export default {
  data() {
    return {
      personnel: 0,
      budget: 0,
      quantity: 0,
      price: 0,
      dialog: false,
      total: 0,
      budgetTitle: "",
      list: [],
      budgetListBarClass: {
        "정육/계란류": "progress-bar bg-danger",
        채소: "progress-bar bg-success",
        "생수/음료": "progress-bar bg-primary",
        라면: "progress-bar bg-warning"
      }
    };
  },

  created() {},
  watch: {
    list: {
      handler: function(newVal) {
        let sum = 0;
        for (let i = 0; i < this.list.length; i++) {
          sum += newVal[i].quantity * newVal[i].pro_price;
        }
        this.total = sum;
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    changeRecommendBar() {
      console.log("changeRecommendBar emit!");
      this.$emit("changeRecommendBar", this.budget);
    },
    cookie() {
      this.$cookie.set("test", this.personnel, 1);
    },
    triggerEvent() {
      this.$store.state.personnel = this.personnel;
      this.$store.state.budget = this.budget;
    },
    stringNumberToInt(stringNumber) {
      return parseInt(stringNumber.replace(/,/g, ""));
    },
    budgetSave(bool) {
      if (bool === true) {
        if (!sessionStorage.getItem("accessToken")) {
          alert("저장 기능은 로그인 후 이용해주세요");
          return;
        }
        if (this.budgetTitle == "") {
          alert("제목을 입력해주세요.");
          return;
        }
        if (this.personnel == "") {
          alert("인원을 입력해주세요.");
          return;
        }
        if (this.budget == "") {
          alert("예산을 입력해주세요.");
          return;
        }
        let budget_info = {
          user_email: sessionStorage.getItem("email"),
          budget_title: this.budgetTitle,
          personnel: this.personnel,
          budget: this.budget
        };

        let budget_list = this.list;

        let budget = {
          user_email: sessionStorage.getItem("email"),
          budget_title: this.budgetTitle,
          personnel: this.personnel,
          budget: this.budget,
          budget_list: this.list
        };
        http
          .post(
            "/budget",
            {
              budgetinfo: {
                user_email: sessionStorage.getItem("email"),
                budget_title: this.budgetTitle,
                personnel: this.personnel,
                budget: this.budget
              },
              budgetlist: this.list
            },
            this.$store.getters.requestHeader
          )
          .then(response => {
            this.personnel = 0;
            this.budget = 0;
          })
          .catch(ex => {});
        this.$store.dispatch("allClearAsync");
        this.$router.push("/mybudget");
      } else {
        this.dialog = false;
      }
    },
    total_sum(val) {
      this.total += val;
      return this.total;
    },
    del_pro(i) {
      this.$store.dispatch("deleteProductAsync", i);
    },

    changeQuantity(budget, idx, event) {
      const oldvalue = this.computedBudgetList[idx].quantity;
      const newvalue = event.target.value;
      let ope = "";
      if (oldvalue > newvalue) {
        // 감소
        ope = "m";
      } else if (oldvalue < newvalue) {
        // 증가
        ope = "p";
      }
      let changeInfo = {
        isETC: budget.isETC,
        ope: ope,
        idx: idx,
        price: budget.price,
        id: budget.pro_id,
        category: budget.category,
        changeQuantity: newvalue
      };
      this.$store.dispatch("changeQuantityAsync", changeInfo);
    }
  },
  mounted() {
    this.personnel = this.$store.state.personnel;
    this.budget = this.$store.state.budget;
  },
  computed: {
    computedBudgetList() {
      this.list = this.$store.state.budgetlist;
      return this.$store.state.budgetlist;
    }
  },

  craeted() {}
};
</script>
<style scoped>
.product_table {
  overflow-y: scroll;
  overflow-x: hidden;
  width: 100%;
}

.product {
  padding-top: 8px;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 130px;
  color: darkblue;
  font-weight: bold;
}

.myButton {
  box-shadow: 0px 1px 0px 0px #fff6af;
  background: linear-gradient(to bottom, #ffec64 5%, #ffab23 100%);
  background-color: #ffec64;
  border-radius: 6px;
  border: 1px solid #ffaa22;
  display: inline-block;
  cursor: pointer;
  color: #333333;
  font-family: Arial;
  font-size: 12px;
  font-weight: bold;
  padding: 5px 7px;
  text-decoration: none;
  text-shadow: 1px 1px 50px #ffee66;
}
.myButton:hover {
  background: linear-gradient(to bottom, #ffab23 5%, #ffec64 100%);
  background-color: #ffab23;
}
.myButton:active {
  position: relative;
  top: 1px;
}
</style>