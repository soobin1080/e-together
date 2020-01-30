<template>
  <v-layout mt-5 wrap>
    <v-flex  v-for="i in product.length > limits ? limits : product.length" xs12 sm6 lg4 :key="i">
      <Product
        class="ma-3"
        :pro_name="product[i - 1].pro_name"
        :price="product[i - 1].price"
        :img="product[i - 1].img"
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
import http from "../http-common";
import Product from "@/components/Product";

export default {
  name: "ProductsList",
  props: {
    limits: { type: Number, default: 454 },
    loadMore: { type: Boolean, default: false },
    product: {type: Object}
  },
  data() {
    return {
    };
  },
  components: {
    Product
  },
 
  created() {
    // console.log("create: productlist에서 뿌려줘!!!!:" + this.keyword);
  },
  mounted() {
    // console.log("mount: productlist에서 뿌려줘!!!!:" + this.keyword);
    this.all();
  },
  methods: {
    all() {
      product=this.$attrs.product
    },
    
    loadMoreProducts() {
      this.limits = this.limits + 4;
      if (this.limits >= this.products.length) {
        this.loadMore = false;
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
