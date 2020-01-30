<template>
  <div>
    <v-img :src="getImgUrl('supermarket-949913_1920.jpg')" aspect-ratio="5.5"></v-img>
    <v-row class="main" style="padding-top:120px">
      <v-col lg="8">
        <!-- search box -->
        <v-text-field
          width="100px"
          flat
          hide-details
          label="Search"
          prepend-inner-icon="search"
          solo-inverted
          shaped
          v-model="keyword"
          v-on:keyup.enter="getProductList(keyword)"          
        ></v-text-field>

        <b-card no-body style="height:700px;">
          <!-- 카테고리 탭 -->
          <b-tabs small card>
            <b-tab title="전체">
              <ProductList :product="products"></ProductList>
            </b-tab>
            <b-tab title="과일">
             
            </b-tab>
            <b-tab title="채소">
              <!-- <ProductList></ProductList> -->
            </b-tab>
            <b-tab title="밥/라면">Sibzamini!</b-tab>
            <b-tab title="음료">I'm the last tab</b-tab>
            <b-tab title="과자">I'm the last tab</b-tab>
          </b-tabs>

          <!-- modal 플로팅 버튼-->
          <v-btn
            absolute
            dark
            fab
            bottom
            right
            color="#ffd900"
            v-b-modal.modal-1
            class="hidden-lg-only"
            
          >
            <i class="material-icons">shopping_cart</i>
          </v-btn>
        </b-card>
      </v-col>

      <!-- 장보기 내역 -->
      <v-col>
      <v-flex d-none d-lg-flex>
          <BudgetList></BudgetList>
      </v-flex>
       </v-col>

     
    </v-row>
     <!-- modal 창 -->
      <b-modal id="modal-1" scrollable>
        <BudgetList></BudgetList>
      </b-modal>
  </div>
</template>
<script>
import http from "../http-common";
import BudgetList from "../components/BudgetList";
import ProductList from "../components/ProductList";

export default {
  components: {
    BudgetList,
    ProductList
  },
  
  data() {
    return {
      keyword: "",
      products: Object    
    };
  },
 mounted(){
   this.getProductList(this.keyword);
 },

  methods: {    
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    // search(keyword){
    //   console.log("/////검색어: "+keyword+" "+this.keyword);
    //   this.keyword=keyword;
      
    // },
    getProductList(keyword) {
      // this.$emit('search');
      this.keyword=keyword;
      console.log("키워드는" + this.keyword);
      if (this.keyword != "" && this.keyword.length > 0) {
        this.search();
      } else {
        this.all();
      }
    },
    all() {
      http
        .get("/product")
        .then(response => {
          console.log("!!!!!!!!!!!!"+response.data);
          this.products = response.data;
          console.log("!!!!!!!!!!product: "+this.products);
          // console.log(this.products.length);
        })
        .catch(() => {
          this.errored = true;
          // alert("error!!");
        })
        .finally(() => (this.loading = false));
    },
    search() {      
        http
        .get("/product/" + this.keyword)
        .then(response => {
          console.log(response.data);
          this.products = response.data;
        })
        .catch(() => {
          this.errored = true;
          // alert("error!!");
        })
        .finally(() => (this.loading = false));
    },
  }
};
</script>

<style scoped>
.main {
  padding-bottom: 120px;
  margin: auto;
  width: 80%;
}
</style>
