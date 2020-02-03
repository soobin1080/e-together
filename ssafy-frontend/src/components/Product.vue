<template>
  <v-card height="260px">
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
      this.$store.state.list.pro_name = this.pro_name;
      this.$store.state.list.price = this.price;
      this.$store.state.list.quantity = this.quantity;
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
  width: 140px;
}

.grey--text {
  font-size: 11pt;
  /* 한 줄 자르기 */
  display: inline-block;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>

