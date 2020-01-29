<template>
  <v-list-item @click="">

    <v-list-item-avatar>
      <i class="fas fa-folder-open"></i>
    </v-list-item-avatar>
   
    <v-list-item-content>
      <v-list-item-title>{{title}}</v-list-item-title>
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

    },

    methods: {
      async getMyBudget() {
        this.mybudgets = await FirebaseService.getMyBudgets();
      }

    },
    mount() {
      this.getMyBudget();
    }

  }
</script>

<style scoped>

</style>