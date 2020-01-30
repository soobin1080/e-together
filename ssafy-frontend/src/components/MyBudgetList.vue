<template>
  <v-list two-line subheader>
    <v-list-item v-for="budget in computedPagingBudgets" :key="budget.created_at">
      <MyBudget 
        :date="budget.created_at.toString()" 
        :title="budget.title" 
        :body="budget.body"
        >
      </MyBudget>

    </v-list-item>
    <!-- <v-divider></v-divider> -->
    <!-- <i class="fas fa-chevron-circle-down"></i> -->
  </v-list>
</template>

<script>
  import FirebaseService from "@/services/FirebaseService";
  import MyBudget from "./MyBudget";
  export default {
    name: "MyBudgetList",

    components: {
      MyBudget
    },

    props: {
      limits: {
        type: Number,
        default: 6
      },
      loadMore: {
        type: Boolean,
        default: false
      },
      pages : {
        type: Number
      },
      pagingList: {
        type: Array,
        required: true
      },
      allLength: {
        type: Number,
        required: true
      }
    
    },


    data() {
      return {
        pagingbudgets: [],
      }
    },

    methods: {
      // async getMyBudgets() {
      //   this.mybudgets = await FirebaseService.getMyBudgets();
      // }

    },
    computed : {
      computedPagingBudgets: function(){
        this.pagingBudgets = []
        // let currentPage = this.pages
        // console.log(currentPage*5-5)
        // console.log(this.pagingList)
        // console.log("computedBudgetList active")
        let tmp = 0;
        for (let i = (this.pages*5)-5; i < this.pages*5; i++) {
          console.log("i : "+i)
          if (i === (this.allLength)) {
            this.limits = tmp
            console.log(tmp)
            break
            }
          else {
            this.pagingBudgets.push(this.pagingList[i])
          }
          tmp++
        }
        console.log(this.pagingBudgets)
        return this.pagingBudgets
      }
    },
    mounted() {

    }

  }
</script>

<style scoped>

</style>