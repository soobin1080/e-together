<template>
  <div>
    <input type="text" name="title" v-model="title" />
    <input type="text" name="body" v-model="body" />
    <v-btn @click="postMyBudgets(title, body)">add</v-btn>
    <ImgBanner>
      <div class="text-center text-white" style="line-height:1.2em;font-size:2.5em;" slot="text" v-resize-text>My Budget
      </div>
    </ImgBanner>
    <v-card style="width:80%;" class="mx-auto my-5">
      <MyBudgetList :currentPage="pages" :limits="5" :loadMore="true" ref="mybudgetlist"></MyBudgetList>
    </v-card>

    
    <div class="text-center">
      {{pages}}
    <v-pagination
      v-model="pages"
      :length="15"
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
      pageLength: 10,
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
        // this.$refs.mybudgetlist.getMyBudgets()
      }

    },
    computed: {

    },
    mounted() {
      //this.$root.$emit('asyncBudgetList')

    }
  }
</script>

<style scoped>



</style>