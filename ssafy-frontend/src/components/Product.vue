<template>
  <v-card height="300px">
    <v-img :src="img" height="170px"></v-img>
    <v-card-title primary-title>
      <div>
        <v-tooltip top>
          <template v-slot:activator="{ on }">
            <div class="lightgrey--text" v-on="on">{{pro_name}}</div>
          </template>
          <span>{{pro_name}}</span>
        </v-tooltip>
        <div class="black--text" style="font-size:12pt;">
          수량:
          <input
            type="number"
            placeholder="1"
            min="1"
            size="small"
            style="text-align:center; font-size:12pt; width:50px"
            inline
            controls
            v-model="quantity"
            :quantity="quantity"
          />
          <div class="grey--text" style="float:right">{{price}}원</div>

          <div>
            <v-btn small color="#ffd900" style="float:right" @click="addcart">+</v-btn>
          </div>
        </div>
      </div>
    </v-card-title>
  </v-card>
</template>
<script>
export default {
  name: "Product",
  props: {
    pro_name: { type: String },
    price: { type: String },
    img: { type: String }
  },
  data: () => ({
    quantity: 1
  }),
  methods: {
    addcart() {
      this.$store.state.pro_name = this.pro_name;
      this.$store.state.price = this.price;
      this.$store.state.quantity = this.quantity;
    }
  }
};
</script>
<style scoped>
.lightgrey--text {
  font-size: 13pt;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 190px;
}

.grey--text {
  font-size: 12pt;
  /* 한 줄 자르기 */
  display: inline-block;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>

