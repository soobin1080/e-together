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
        <div class="grey--text">{{price}}원</div>
        <div class="lightgrey--text">
          수량:
          <input
            type="number"
            placeholder="0"
            size="small"
            style="text-align:center; width:60px"
            inline
            controls
          />
          <v-btn small color="#ffd900" @click="buy">+</v-btn>
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

  methods: {
    buy() {
      this.$store.state.pro_name = this.pro_name;
      this.$store.state.price = this.price;
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
  text-overflow: ellipsis; /* 여러 줄 자르기 추가 스타일 */
  /* white-space: normal;
  line-height: 1.2;
  height: 4.6em;
  text-align: left;
  word-wrap: break-word;
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical; */
}
</style>

