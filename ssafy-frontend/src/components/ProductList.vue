<template>
  <v-layout mt-5 wrap>
    <v-flex v-for="i in products.length > limits ? limits : products.length" xs12 sm6 lg4 :key="i">
      <Product
        class="ma-3"
        :pro_name="products[i - 1].pro_name"
        :price="products[i - 1].price"
        :img="products[i - 1].img"
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
    keyword: { type: String, default: "" }
  },
  data() {
    return {
      products: []
    };
  },
  components: {
    Product
  },
  created() {
    // console.log("create: productlist에서 뿌려줘!!!!:" + this.keyword);
    this.getProductList();
  },
  mounted() {
    // console.log("mount: productlist에서 뿌려줘!!!!:" + this.keyword);
    this.getProductList();
    this.search();
  },
  methods: {
    getProductList() {
      // this.$emit('search');
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
          console.log(response.data);
          this.products = response.data;
          // console.log("product:"+this.products);
          // console.log(this.products.length);
        })
        .catch(() => {
          this.errored = true;
          // alert("error!!");
        })
        .finally(() => (this.loading = false));
    },
    search() {
      
      console.log("리스트에서 찾아라" + this.keyword);
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
    loadMoreProducts() {
      this.limits = this.limits + 4;
      if (this.limits >= this.products.length) {
        this.loadMore = false;
      }
    }
  },
  computed:{

  }
};
</script>
<style>
.mw-700 {
  max-width: 700px;
  margin: auto;
}
</style>
