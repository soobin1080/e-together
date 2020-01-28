<template>
  <v-layout mt-5 wrap>
    <v-flex
      v-for="i in products.length > limits ? limits : products.length"
      xs12
      sm6
      lg3
      :key="i"
    >
      <Product
        class="ma-3"
        :date="products[i - 1].created_at.toString()"
        :title="products[i - 1].title"
        :body="products[i - 1].body"
        :imgSrc="products[i - 1].img"
      ></Product>
	  <v-divider></v-divider>
    </v-flex>

    <v-flex xs12 text-xs-center round my-5 v-if="loadMore">
      <v-btn color="info" dark v-on:click="loadMoreProducts">
        <v-icon size="25" class="mr-2">fa-plus</v-icon>더 보기
      </v-btn>
    </v-flex>
  </v-layout>
</template>
<script>
import Product from "@/components/Product";
import FirebaseService from "@/services/FirebaseService";

export default {
  name: "ProductsList",
  props: {
    limits: { type: Number, default: 4 },
    loadMore: { type: Boolean, default: false }
  },
  data() {
    return {
      products: []     
    };
  },
  components: {
    Product
  },
  mounted() {
    this.getProducts();    
  },
  methods: {
    async getProducts() {
      this.products = await FirebaseService.getProducts();
      if(this.limits>=this.products.length){
       this.loadMore=false;
      }
    },
    loadMoreProducts() {
      this.limits=this.limits+4;
      if(this.limits>=this.products.length){
        this.loadMore=false;
      }
    }
  }
};
</script>
<style>
.mw-700 {
  max-width: 700px;
  margin: auto;
}
</style>
