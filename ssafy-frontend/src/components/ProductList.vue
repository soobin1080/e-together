<template>
  <v-layout mt-5 wrap>
    <v-flex v-for="product in computedPagingProducts" xs12 sm6 lg4 :key="product.pro_name">
      <Product class="ma-3" :pro_name="product.pro_name" :price="product.price" :img="product.img"></Product>
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
  data() {
    return {
      pagingProductList: []
    };
  },
  props: {
    limits: { type: Number, default: 454 },
    loadMore: { type: Boolean, default: false },
    product: { type: Object },
    productPerPage: {
      type: Number
    },
    products: {
      type: Array
    },
    pages: {
      type: Number,
      required: true
    },
    category: {
      type: String,
      required: true
    }
  },
  data() {
    return {};
  },
  components: {
    Product
  },
  methods: {
    all() {
      product = this.$attrs.product;
    },

    loadMoreProducts() {
      this.limits = this.limits + 4;
      if (this.limits >= this.products.length) {
        this.loadMore = false;
      }
    }
  },
  computed: {
    computedPagingProducts() {
      console.log("productlist computed");
      this.pagingProductList = [];

      let tempList = [];
      if (this.category !== "전체") {
        tempList = this.products.filter(product => {
          // console.log("productlist computed")
          return product.main_category === this.category;
        });
      } else {
        tempList = this.products;
      }

      let start = (this.pages - 1) * this.productPerPage;
      let end = (this.pages - 1) * this.productPerPage + this.productPerPage;

      if (end > tempList.length) {
        end = tempList.length;
      }
      for (let i = start; i < end; i++) {
        this.pagingProductList.push(tempList[i]);
      }
      //console.log("pagingProudct : " + this.pagingProductList)
      return this.pagingProductList;
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
