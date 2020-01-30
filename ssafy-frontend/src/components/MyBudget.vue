<template>
  <v-list-item>

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
    date: { type: String },
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
      
    }

  }
</script>

<style scoped>

</style>