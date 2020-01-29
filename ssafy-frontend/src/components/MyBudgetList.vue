<template>
  <v-list two-line subheader>
    <v-list-item v-for="i in computedMyBudgets.length > limits ? limits : computedMyBudgets.length" :key="i">
      <MyBudget 
        :date="mybudgets[i-1].created_at.toString()" 
        :title="mybudgets[i-1].title" 
        :body="mybudgets[i-1].body"
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
      }
    },

    data() {
      return {
        mybudgets: [],
      }
    },

    methods: {
      async getMyBudgets() {
        this.mybudgets = await FirebaseService.getMyBudgets();
      }

    },
    computed : {
      computedMyBudgets: function(){
        this.getMyBudgets()
        return this.mybudgets
      }
    },
    mounted() {
      this.getMyBudgets();
    }

  }
</script>

<style scoped>

</style>