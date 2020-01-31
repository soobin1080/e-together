<template>
  <div>
    <!-- <input type="text" name="title" v-model="title" /> -->
    <!-- <input type="text" name="body" v-model="body" /> -->
    <!-- <v-btn @click="postMyBudgets(title, body)">add</v-btn> -->
    <ImgBanner>
      <div class="text-center text-white" style="line-height:1.2em;font-size:2.5em;" slot="text" v-resize-text>My Budget
      </div>
    </ImgBanner>
    <v-card style="width:80%;" class="mx-auto my-5">
      <MyBudgetList 
        :pages="pages" 
        :loadMore="true" 
        :pagingList="pagingList" 
        :allLength="allLength"
        ref="mybudgetlist"></MyBudgetList>
    </v-card>

    
    <div class="text-center">
      {{pages}}
    <v-pagination
      v-model="pages"
      :length="pagingLength"
      total-visible="9"
    ></v-pagination>
    </div>

  </div>
</template>

<script>
  import FirebaseService from "@/services/FirebaseService";
  import ImgBanner from '../components/ImgBanner'
  import MyBudgetList from '../components/MyBudgetList'
  export default {
    name: "MyListPage",

    components: {
      ImgBanner,
      MyBudgetList,
      //PortfolioList,
    },
    data: () => ({
      title: "",
      body: "",
      pages: 1,
      allPages: [],
      pagingList: [],
      pagingLength: 0,
      allLength: 0,
      budgetPerPage: 5,
      items: [{
          icon: 'folder',
          iconClass: 'grey lighten-1 white--text',
          title: 'Photos',
          subtitle: 'Jan 9, 2014'
        },
        {
          icon: 'folder',
          iconClass: 'grey lighten-1 white--text',
          title: 'Recipes',
          subtitle: 'Jan 17, 2014'
        },
        {
          icon: 'folder',
          iconClass: 'grey lighten-1 white--text',
          title: 'Work',
          subtitle: 'Jan 28, 2014'
        },
      ],
    }),
    methods: {
      getImgUrl(img) {
        return require("../assets/" + img);
      },
      postMyBudgets(title, body) {
        title = this.title
        body = this.body
        FirebaseService.postMyBudgets(title, body)
        this.$refs.mybudgetlist.getMyBudgets()
      },
      async getMyBudgets() {
        console.log("active")
        this.pagingList= await FirebaseService.getMyBudgets();
        return this.pagingList
    },
    },
    computed: {
      // computedPagingList : () => ({
      //   async getMyBudget() {
      //     console.log("active")
      //     this.pagingList= await FirebaseService.getMyBudgets();
      //     return this.paginList
      //   }
      // })
    },
   async mounted() {
     this.allPages = await FirebaseService.getMyBudgets()
     this.allLength = this.allPages.length
     console.log("allLength : " + this.allPages.length)
     this.pagingList= await FirebaseService.getMyBudgetPaging(((this.pages-1)*5), ((this.pages-1)*5)+5, this.allLength)
     console.log(this.pagingList)
     
     if (this.allLength % this.budgetPerPage === 0) {
       this.pagingLength = parseInt(this.allLength / 5)
     } else {
       this.pagingLength = parseInt((this.allLength / 5) + 1)
     }
     console.log("pagingLength : "+this.pagingLength)
      //this.getMyBudgets()
  }
  }
</script>

<style scoped>



</style>