<template>
<div>
<v-img :src="getImgUrl('supermarket-949913_1920.jpg')" aspect-ratio="5.5"></v-img>
  <b-row class="main" style="padding-top:120px">
    <b-col cols="12" lg="8">
      <!-- Tabs with card integration -->
      <v-text-field
        width="100px"
        flat
        hide-details
        label="Search"
        prepend-inner-icon="search"
        solo-inverted
        shaped
      ></v-text-field>
      <b-card no-body style="height:700px;">
        <b-tabs v-model="tabIndex" small card>
          <b-tab title="전체">전체 페이지
            <ProductList></ProductList>
          </b-tab>
          <b-tab title="고기">고기 페이지</b-tab>
          <b-tab title="야채">
            I'm the second tab
            <b-card>
              I'm the card in tab
              <b-tabs v-model="tabIndex" small card>
                <b-tab title="전체"></b-tab>
                <b-tab title="고기">고기 페이지</b-tab>
              </b-tabs>
            </b-card>
          </b-tab>
          <b-tab title="밥/라면">Sibzamini!</b-tab>
          <b-tab title="음료">I'm the last tab</b-tab>
          <b-tab title="과자">I'm the last tab</b-tab>
        </b-tabs>
        <v-btn
          absolute
          dark
          fab
          bottom
          right
          color="#ffd900"
          v-b-modal.modal-1
          class="hidden-lg-only"
          @click="budget"
        >
          <i class="material-icons">shopping_cart</i>
        </v-btn>
      </b-card>
    </b-col>

    <b-modal id="modal-1" scrollable="true">
      <BudgetList></BudgetList>
    </b-modal>
    <v-flex d-none d-lg-flex>
      <b-col>        
        <BudgetList></BudgetList>       
      </b-col>
    </v-flex>
  </b-row>
  </div>
</template>
<script>
import BudgetList from "../components/BudgetList";
import ProductList from '../components/ProductList'

export default { 
  components: {
    BudgetList,
    ProductList
  },
  methods: {
    budget() {
      
    },
    getImgUrl(img) {
      return require("../assets/" + img);
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
