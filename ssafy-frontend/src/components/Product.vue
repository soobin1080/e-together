<template>
  <v-card class="mx-auto" max-width="400" min-height="340">
    <v-img class="white--text align-end" height="200px" :src="img"></v-img>
    <v-tooltip top>
      <template v-slot:activator="{ on }">
        <!-- <div class="lightgrey--text" v-on="on">{{pro_name}}</div> -->
        <v-card-title v-on="on" class="font-weight-bold lightgrey--text">{{pro_name}}</v-card-title>
      </template>
      <span>{{pro_name}}</span>
    </v-tooltip>

    <v-card-subtitle class="card-second-line" v-if="weight">
      <p class="pb-0 mb-0" style="float:left; font-size:0.7rem">{{weight}}</p><br>
      <p class="pb-0 mb-0" style="float:right; font-size:0.9rem; font-weight:bold">{{price}}원</p>
    </v-card-subtitle>
    <v-card-subtitle class="card-second-line" v-else>
      <p class="pb-0 mb-0" style="float:left; font-size:0.7rem"></p><br>
      <p class="pb-0 mb-0" style="float:right; font-size:0.9rem; font-weight:bold">{{price}}원</p>
    </v-card-subtitle>

    <v-card-subtitle class="card-last-line">
      <p style="float:left" class="mr-5">
        수량:
        <input
          type="number"
          placeholder="1"
          min="1"
          size="small"
          style="text-align:center; font-size:10pt; width:40px"
          inline
          controls
          v-model="quantity"
          :quantity="quantity"
        />
      </p>
      <p style="float:right">
      <i
        class="far fa-plus-square"
        style="cursor:pointer; color:orange;font-size:1.2rem;"
        @click="addcart"
      ></i>
      </p>
    </v-card-subtitle>
  </v-card>
  <!-- <v-card height="260px">
    <v-img :src="img" height="140px"></v-img>
    <v-card-title>
      <div>
        <v-tooltip top>
          <template v-slot:activator="{ on }">
            <div class="lightgrey--text" v-on="on">{{pro_name}}</div>
          </template>
          <span>{{pro_name}}</span>
        </v-tooltip>
        <div class="grey--text" style="float:right">{{price}}원</div>
        <br />
        <div class="black--text" style="font-size:10pt; text-align:right">
          수량:
          <input
            type="number"
            placeholder="1"
            min="1"
            size="small"
            style="text-align:center; font-size:10pt; width:40px"
            inline
            controls
            v-model="quantity"
            :quantity="quantity"
          />

          <v-btn small right color="#ffd900" style="float:right" @click="addcart">+</v-btn>
        </div>
      </div>
    </v-card-title>
  </v-card>-->
</template>
<script>
import EventBus from "../event-bus.js";
export default {
  name: "Product",
  props: {
    pro_id: { type: String },
    pro_name: { type: String },
    price: { type: Number },
    weight: { type: String },
    img: { type: String },
    main_category: { type: String },
    product: { type: Object }
  },
  data: () => ({
    quantity: 1,
    ETC: [
      "수산물/해산물",
      "쌀/잡곡",
      "즉석식품",
      "과일",
      "스낵",
      "견과/건해산물"
    ],
    colorByCategory: {
      "정육/계란류": "bg-danger",
      "생수/음료": "bg-primary",
      채소: "bg-success",
      라면: "bg-warning",
      기타: "bg-secondary"
    }
  }),
  methods: {
    addcart() {
      alert("장바구니에 담았습니다!");
      // console.log(this.product);

      // console.log(
      //   "/////////////" + this.pro_id + "             " + this.main_category
      // );
      // console.log(this.product)
      console.log(this.colorByCategory["정육/계란류"]);

      let product = {
        pro_id: this.pro_id,
        pro_name: this.pro_name,
        price: Number(this.price),
        quantity: Number(this.quantity),
        pro_price: Number(this.price) * Number(this.quantity),
        category: this.product.main_category,
        weight: this.weight,
        isETC: this.ETC.includes(this.product.main_category)
      };
      console.log(product);
      this.$store.dispatch("addCartAsync", product);
      this.$store.dispatch("addBudgetBarAsync", product);
      // EventBus.$emit("addCart", product);
    }
  }
};
</script>
<style scoped>
.lightgrey--text {
  font-size: 12pt;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 180px;
}

.grey--text {
  width: 90%;
  font-size: 0.8rem;
  /* 한 줄 자르기 */
  /* display: inline-block; */
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.card-second-line {
  padding-top:2px;
  margin: 0 0 2px 0; 
  padding-bottom:2px;
}

.card-last-line {
  margin-top: 5px;
}
</style>

