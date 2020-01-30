<template>
  <div>
    <v-img :src="getImgUrl('supermarket-949913_1920.jpg')" aspect-ratio="5.5"></v-img>
    <!-- page navigation-->
    <div class="text-center">
      {{pages}}
    <v-pagination
      v-model="pages"
      :length="pagingLength"
      total-visible="9"
    ></v-pagination>
    </div>

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
          <b-tabs small card :tabs="tabs">
              <b-tab v-for="tab in tabs" :key="tab.title" :title="tab.title" @click="clickTab">
                  <ProductList 
                    :products="products" 
                    :pages="pages" 
                    :category="category"
                    :productPerPage="productPerPage"></ProductList>
              </b-tab>
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
      <b-col>
      <v-flex d-none d-lg-flex>
          <BudgetList></BudgetList>
      </v-flex>
       </b-col>

      <!-- modal 창 -->
      <b-modal id="modal-1" scrollable>
        <BudgetList></BudgetList>
      </b-modal>
    </v-row>
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
      products: [],
      tabs: [
        {title : "전체"},
        {title : "정육/계란류"},
        {title : "수산물/해산물"},
        {title : "채소"},
        {title : "쌀/잡곡"},
        {title : "라면"},
        {title : "즉석식품"},
        {title : "생수/음료"},
        {title: "과일"},
        {title : "스낵"},
        {title : "견과/건해산물"},
      ],
      pagingProduct: [],
      pages: 1,
      productPerPage: 9,
      pagingLength: 10,
      category: "전체",
    };
  },
 mounted(){
   this.getProductList(this.keyword)
 },
 computed : {
  //  selectedTab : function(title){
  //    consloe.log(title)
  //    this.category = title
  //    return
  //  }
 },

  methods: {
    clickTab : function(event) {
      console.log(event.target.text)
      this.category = event.target.text
      return
    },
    selectTab : function(title) {
      this.category = title
      this.pages = 1
    },
    // selectTab: function(title) {
    //   console.log(title)
    //   if (title !== "전체") {
    //     // console.log("not all")
    //     this.pagingProduct = this.products.filter(product => {
    //       console.log("not all")
    //       return product.main_category == title
    //     })
    //   }
    //   else {
        
    //   }
    // },  
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
         // console.log("!!!!!!!!!!!!"+response.data);
          this.products = response.data;
          //console.log("!!!!!!!!!!product: "+this.products);
          console.log(this.products);
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
          //console.log(response.data);
          this.products = response.data;
          return this.products
        })
        .catch(() => {
          this.errored = true;
          // alert("error!!");
        })
        .finally(() => (this.loading = false));
    },
  },
};
</script>

<style scoped>
.main {
  padding-bottom: 120px;
  margin: auto;
  width: 80%;
}
</style>
