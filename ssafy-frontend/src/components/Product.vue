<template>
  <v-card height="360px">
    <v-img :src="imgSrc" height="200px"></v-img>
    <v-card-title primary-title>
      <div>
        <div class="headline">{{title}}</div>
        <span class="grey--text">{{body}}</span>
        <input type="number" placeholder="수량" size="small" style="text-align:center; width:60px" inline controls>
      </div>
      <v-btn @click="buy">+</v-btn>
    </v-card-title>
    
  </v-card>
</template>

<script>
export default {
  name: "Product",
  props: {
    date: { type: String },
    title: { type: String },
    body: { type: String },
    imgSrc: { type: String }
  },
  data() {
    return {
      //
    };
  },
  methods:{
    buy(){
      this.$store.state.title=this.title;
      this.$store.state.body=this.body;
    }
  }
};
</script>

<style scoped>
.headline {
  display: inline-block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap; 
}
.grey--text {
  font-size: 12pt;
  /* 한 줄 자르기 */
  display: inline-block;  
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; /* 여러 줄 자르기 추가 스타일 */
  white-space: normal;
  line-height: 1.2;
  height: 4.6em;
  text-align: left;
  word-wrap: break-word;
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;
}
</style>

