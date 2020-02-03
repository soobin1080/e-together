<template>
 
</template>

<script>
  export default {
    name: "MyBudgetDetail",
    props: {
    date: { type: Number },
    title: { type: String },
    body: { type: String },
  },

    data() {
      return {

      }
    },

    methods: {
      show_detail(num) {
      
    }

    },
    mount() {

    },

    computed: {
     convertDate() {
       console.log("convertDate")
       let convertDate = new Date(this.date)
       console.log(convertDate)
       return this.date
     }
    }

  }
</script>

<style scoped>

</style>