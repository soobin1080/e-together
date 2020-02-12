<template>
  <v-card height="330px">

      <v-img src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg" height="194"></v-img>
      <div>
        <div>
          <v-card-title> 
            {{review.budget_title}}
          </v-card-title>
          <v-card-subtitle>
            {{review.user_name}}
          </v-card-subtitle>
        </div>
        <div>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn icon>
              <v-icon>mdi-heart</v-icon>
            </v-btn>
            <v-btn icon>
              <v-icon>mdi-share-variant</v-icon>
            </v-btn>
          </v-card-actions>
        </div>
      </div>
    </v-card>
</template>

<script>
import ResizeText from "vue-resize-text";
export default {
  name : "Review",

  
  data() {
    return{}
  },

  props: {
    review : {
      type : Object,
    }
  },

  methods: {

  }
}
</script>

<style scoped>

</style>