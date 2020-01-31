<template>
  <v-list-item @click="">

    <v-list-item-avatar>
      <i class="fas fa-folder-open"></i>
    </v-list-item-avatar>
   
    <v-list-item-content>
      <v-list-item-title>{{title}}, {{date}}</v-list-item-title>
      <v-list-item-subtitle>{{body}}</v-list-item-subtitle>
    </v-list-item-content>

  </v-list-item>
</template>

<script>
  export default {
    name: "MyBudget",
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