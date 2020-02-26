<template>
  <div>
    <ImgBanner>
      <div
        class="text-center text-white"
        style="line-height:1.2em;font-size:2.5em;"
        slot="text"
        v-resize-text
      >Mart</div>
    </ImgBanner>

    <!-- 비율 추천 그래프 -->
    <div class="mt-12" >
      <p style="margin:auto; width:70%; color:dimgrey;">
        <i class="material-icons">assessment</i>예산 별 비율 추천        
      </p>
      <div 
        class="progress text-center mx-auto" 
        style="width: 70%;" 
        v-on="on" 
        v-if="canRecommend">
        <div
          v-for="bar in recommendBar"
          :key="bar.className"
          :class="bar.className"
          :style="{width: (bar.price / (recommendTotal + recommendETCTotal) * 100) +'%'}"
        >
          <!-- {{bar.price}} -->
          <div v-if="bar.price > 0">
            <v-tooltip bottom>
              <template v-slot:activator="{ on }">
                <span v-on="on">{{bar.category}}</span>
              </template>
              <span>{{bar.category}} : {{numberCut(bar.price / (recommendTotal + recommendETCTotal) * 100)+'%'}}</span>
            </v-tooltip>
          </div>
        </div>

        <div
          v-if="recommendETCTotal > 0"
          class="progress-bar bg-secondary"
          v-bind:style="{width: (recommendETCTotal / (recommendTotal + recommendETCTotal)) * 100+'%'}"
        >
          <v-tooltip bottom>
            <template v-slot:activator="{ on }">
              <span v-on="on">기타</span>
            </template>
            <span v-for="bar in recommendBarETC" :key="bar.className">
              <span v-if="bar.price > 0">
                {{bar.category}} : {{numberCut(bar.price / (recommendTotal + recommendETCTotal) * 100)+'%'}}
                <br />
              </span>
            </span>
          </v-tooltip>
        </div>
      </div>
      <br>
      <p style="margin:auto; padding-top:5px; width:70%; color:dimgrey">
        <i class="material-icons">assessment</i>현재 내 예산 비율
      </p>
      <div class="progress text-center mx-auto" style="width: 70%; height: 15%;" v-on="on">
        <!-- <div v-for="category in computedBudgetListBar" class="progress-bar">{{category.category}}</div> -->
        <div
          v-for="bar in getMainBar"
          :key="bar.className"
          :class="bar.className"
          :style="{width: (bar.price / (getMainTotal + getETCTotal)) * 100+'%'}"
        >
          <div v-if="bar.price > 0">
            <v-tooltip bottom>
              <template v-slot:activator="{ on }">
                <span v-on="on">{{bar.category}}</span>
              </template>
              <span>{{numberCut((bar.price / (getMainTotal + getETCTotal)) * 100)+'%'}} / {{bar.price}}원</span>
            </v-tooltip>
          </div>
        </div>
        <div
          v-if="getETCTotal > 0"
          class="progress-bar bg-secondary"
          v-bind:style="{width: (getETCTotal / (getMainTotal + getETCTotal)) * 100+'%'}"
        >
          <v-tooltip bottom>
            <template v-slot:activator="{ on }">
              <span v-on="on">기타</span>
            </template>
            <span v-for="bar in getETCBar" :key="bar.className">
              <span v-if="bar.price > 0">
                {{bar.category}} : {{numberCut((bar.price / (getMainTotal + getETCTotal)) * 100)+'%'}}
                <br />
              </span>
            </span>
          </v-tooltip>
        </div>
      </div>
    </div>


<v-flex xs12 md9 lg9 class="main">
    <v-row  style="padding-top:40px">
      <v-col lg="8" style="padding-top:0px;">
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
          v-on:keyup="getProductListByCaterogy(category, pages, keyword)"
        ></v-text-field>

        <b-card no-body style>
          <v-tabs dark background-color="darken-3" show-arrows>
            <v-tabs-slider color="teal lighten-3"></v-tabs-slider>
            <v-tab
              v-for="tab in tabs"
              :key="tab.title"
              @click="getProductListByCaterogy(tab.title, pages, keyword)"
              v-model="category"
            >{{tab.title}}</v-tab>
          </v-tabs>

          <!-- page navigation-->
          <br />
          <div class="text-center">
            <v-pagination 
              v-model="pages" 
              :length="pagingLength"
              @click="getProductListByCaterogy(this.category, pages, '')"
              total-visible="7"></v-pagination>
          </div>

          <ProductList
            :pagingProducts="pagingProducts"
          ></ProductList>
          <!-- 카테고리 탭 -->
          <!-- <b-tabs small card :tabs="tabs">
              <b-tab v-for="tab in tabs" :key="tab.title" :title="tab.title" @click="clickTab">
              </b-tab>
              <ProductList 
                :products="products" 
                :pages="pages" 
                :category="category"
                :productPerPage="productPerPage"></ProductList>
              
          </b-tabs>-->

          <!-- modal 플로팅 버튼-->
          <v-btn
            fixed
            dark
            fab
            bottom
            right
            color="#ffd900"
            @click="modalAppear"
            class="hidden-lg-only"
          >
            <i class="material-icons">shopping_cart</i>
          </v-btn>
        </b-card>
      </v-col>
      <!-- v-b-modal.modal-1 -->
      <!-- 장보기 내역 -->
      <!-- <v-col> -->
      <v-flex d-none d-lg-flex>
        <BudgetList 
          @changeRecommendBar="recommendBudgetBar"
          >
        </BudgetList>
      </v-flex>
      <!-- </v-col> -->
    </v-row>
</v-flex>
    <!-- modal 창 -->
    <v-row justify="center">
      <v-dialog v-model="budgetDialog" scrollable max-width="300px">
        <BudgetList 
          @changeRecommendBar="recommendBudgetBar"
          >
        </BudgetList>
      </v-dialog>
    </v-row>

    <!-- <BudgetModal id="budgetModal">
    </BudgetModal>-->
  </div>
</template>
<script>
import ImgBanner from "../components/ImgBanner";
import http from "../http-common";
import BudgetList from "../components/BudgetList";
import ProductList from "../components/ProductList";
import ResizeText from "vue-resize-text";
import BudgetModal from "../components/BudgetModal";
export default {
  components: {
    ImgBanner,
    BudgetList,
    ProductList,
    BudgetModal
  },
  directives: {
    ResizeText
  },
  data() {
    return {
      keyword: "",
      products: [],
      tabs: [
        { title: "전체" },
        { title: "정육/계란류" },
        { title: "생수/음료" },
        { title: "채소" },
        { title: "라면" },
        { title: "수산물/해산물" },
        { title: "즉석식품" },
        { title: "쌀/잡곡" },
        { title: "과일" },
        { title: "스낵" },
        { title: "견과/건해산물" }
      ],
      pagingProducts: [],
      pages: 1,
      productPerPage: 12,
      pagingLength: 10,
      category: "전체",
      allLegnth: 0,
      pagingLength: 0,
      budgetDialog: false,
      budgetList: [],
      etcTotal: 0,
      mainTotal: 0,
      recommendBar: [
        {
          category: "정육/계란류",
          price: 0,
          className: "progress-bar bg-danger"
        },
        {
          category: "생수/음료",
          price: 0,
          className: "progress-bar bg-primary"
        },
        {
          category: "채소",
          price: 0,
          className: "progress-bar bg-success"
        },
        {
          category: "라면",
          price: 0,
          className: "progress-bar bg-warning"
        }
      ],
      recommendBarETC: [
        {
          category: "수산물/해산물",
          price: 0,
          className: "progress-bar bg-primary"
        },
        {
          category: "쌀/잡곡",
          price: 0,
          className: "progress-bar bg-secondary"
        },
        {
          category: "즉석식품",
          price: 0,
          className: "progress-bar bg-dark"
        },
        {
          category: "과일",
          price: 0,
          className: "progress-bar bg-success"
        },
        {
          category: "스낵",
          price: 0,
          className: "progress-bar bg-warning"
        },
        {
          category: "견과/건해산물",
          price: 0,
          className: "progress-bar bg-info"
        }
      ],
      recommendTotal: 0,
      recommendETCTotal: 0,
      colorByCategory: [
        { "정육/계란류" : "bg-danger" },
        { "생수/음료" : "bg-primary" },
        { "채소" : "bg-success" },
        { "라면" : "bg-warning" },
        { "기타" : "bg-secondary" }
      ]
    };
  },
  mounted() {
    // this.getProductList(this.keyword);
    this.recommendBudgetBar(this.$store.state.budget);
    this.getProductListByCaterogy("정육/계란류", 2, this.keyword);
  },
  computed: {
    // mountedProduct() {
    //   this.getProductList(this.keyword);
    //   return true;
    // },
    getMainBar() {
      return this.$store.state.budgetListBar;
    },
    getETCBar() {
      return this.$store.state.budgetListBarETC;
    },
    getMainTotal() {
      return this.$store.state.mainTotal;
    },
    getETCTotal() {
      return this.$store.state.etcTotal;
    },
    getTotal() {
      return this.$store.state.mainTotal + this.$store.state.etcTotal;
    },
    canRecommend() {
      if ((this.recommendETCTotal > 0 || this.recommendTotal > 0)) {
        return true
      } else {
        alert('비교예산이 없습니다')
        return false
      }
    }
  },
  methods: {
    getProductListByCaterogy(cat, num, key) {
      console.log(cat, num, key)
      let myPage = 0
      if (this.category !== cat) {
        myPage = 1
      } else { 
        myPage = num
      }
      let requestUrl = ""
      if (key !== "") {
        requestUrl = `/product/${key}`
      } else {
        requestUrl = `/product/`
      }
      console.log(requestUrl)
      http
        .get(requestUrl, {
          params: {
            category : cat,
            page: this.pages,
          }
        }, this.$store.getters.requestHeader)
        .then( res => {
          console.log(res)
          // this.pagingLength = res.data
          this.pagingProducts = res.data.productlist
        })
    },
    clickTab: function(title) {
      this.category = title;
      if (this.category !== "전체") {
        this.pagingProduct = this.products.filter(product => {
          return product.main_category === this.category;
        });
      } else {
        this.pagingProduct = this.products;
      }
      this.allLength = this.pagingProduct.length;
      console.log("allLength : " + this.allLength);
      if (this.allLength % this.productPerPage === 0) {
        this.pagingLength = parseInt(this.allLength / this.productPerPage);
      } else {
        this.pagingLength = parseInt(this.allLength / this.productPerPage) + 1;
      }
      this.pages = 1;
    },
    recommendBudgetBar(mybudget) {
      mybudget = Number(mybudget);
      this.recommendTotal = 0;
      this.recommendETCTotal = 0;
      let i = 0;
      while (i < this.recommendBar.length || i < this.recommendBarETC.length) {
        if (i < this.recommendBar.length) {
          this.recommendBar[i].price = 0;
        }
        this.recommendBarETC[i].price = 0;
        i++;
      }
      if (mybudget !== null && mybudget !== undefined && mybudget !== 0) {
        http
          .get("/recommend", {
            params: {
              budget: mybudget
            }
          })
          .then(res => {
            const keys = Object.keys(res.data);
            const vals = Object.values(res.data);
            const categoryDict = this.$store.state.recommendDict;
            for (let i = 0; i < keys.length; i++) {
              if (this.$store.state.ETC.includes(categoryDict[keys[i]])) {
                const idx = this.recommendBarETC.findIndex(bar => {
                  return bar.category === categoryDict[keys[i]];
                });
                this.recommendBarETC[idx].price = vals[i];
                this.recommendETCTotal += vals[i];
              } else {
                const idx = this.recommendBar.findIndex(bar => {
                  return bar.category === categoryDict[keys[i]];
                });
                this.recommendBar[idx].price = vals[i];
                this.recommendTotal += vals[i];
              }
            }
          })
          .catch(err => {
          });
      } else {
      }
    },
    getImgUrl(img) {
      return require("../assets/" + img);
    },
  
    getProductList(keyword) {
      this.keyword = keyword;
      console.log(this.keyword);
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
          this.products = response.data;
          this.pagingProduct = response.data;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    search() {
      http
        .get(`/product/category/${this.keyword}`, {
          keyword: this.keyword
        })
        .then(response => {
          this.products = response.data;
          if (this.products.length % this.productPerPage === 0) {
            this.pagingLength = parseInt(
              this.products.length / this.productPerPage
            );
          } else {
            this.pagingLength =
              parseInt(this.products.length / this.productPerPage) + 1;
          }
          return this.products;
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    modalAppear() {
      this.budgetDialog = true;
    },
    numberCut(number) {
      return number.toFixed(2);
    },
    showdetail(budgetNum) {
      http
        .get(
          `/budget/detail/${budgetNum}`,
          {
            budget_num: budgetNum
          },
          this.$store.getters.RequestHeader
        )
        .then(res => {
          this.$store.state.budgetlist = res.data.budgetlist;
          this.$store.state.personnel = res.data.budgetinfo.personnel
          this.$store.state.budget = res.data.budgetinfo.budget
        });
      },
  }
};
</script>

<style scoped>
.main {
  padding-bottom: 80px;
  margin: auto;
  
}
</style>
© 2020 GitHub, Inc.
Terms
Privacy
Security
Status
Help
Contact GitHub
Pricing
API
Training
Blog
About
