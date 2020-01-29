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
          v-on:keyup.enter="search(keyword)"          
        ></v-text-field>

        <b-card no-body style="height:700px;">
          <!-- 카테고리 탭 -->
          <b-tabs small card>
            <b-tab title="전체">
              <ProductList :keyword=keyword></ProductList>
            </b-tab>
            <b-tab title="과일">
              <!-- <ProductList></ProductList> -->
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
import BudgetList from "../components/BudgetList";
import ProductList from "../components/ProductList";

export default {
  components: {
    BudgetList,
    ProductList
  },
  
  data() {
    return {
      keyword: ""     
    };
  },
 
  methods: {
    
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    search(keyword){
      console.log("/////검색어: "+keyword+" "+this.keyword);
      this.keyword=keyword;
      
    }
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
