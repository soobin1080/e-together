<template>
  <v-layout mt-5 wrap>
    <v-flex v-for="product in computedPagingProducts" xs6 sm4 lg3 :key="product.pro_name">
      <Product class="ma-2"
        :pro_id="product.pro_id" 
        :pro_name="product.pro_name" 
        :price="product.price" 
        :weight="product.weight" 
        :img="product.img" 
        :product="product"></Product>
    </v-flex>
    <small style="padding-top:10px; margin:auto; color:red">* 마트 상품은 e-mart몰 상품을 기반으로 합니다.</small>
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
    pagingProducts: {
      type: Array
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
      //product = this.$attrs.product;
    }
  },
  computed: {
    computedPagingProducts() {
      return this.pagingProducts
      // console.log("productlist computed");
      // this.pagingProductList = [];

      // let tempList = [];
      // if (this.category !== "전체") {
      //   tempList = this.products.filter(product => {
      //     // console.log("productlist computed")
      //     return product.main_category === this.category;
      //   });
      // } else {
      //   tempList = this.products;
      // }

      // let start = (this.pages - 1) * this.productPerPage;
      // let end = (this.pages - 1) * this.productPerPage + this.productPerPage;

      // if (end > tempList.length) {
      //   end = tempList.length;
      // }
      // for (let i = start; i < end; i++) {
      //   this.pagingProductList.push(tempList[i]);
      // }
      // //console.log("pagingProudct : " + this.pagingProductList)
      // return this.pagingProductList;
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
