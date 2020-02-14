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
              <i v-if="isliked == true" class="fas fa-heart" style="color: red;font-size:30px;" @click="like()"></i>
              <i v-else class="far fa-heart" style="color:black;font-size:30px;" @click="like()"></i>
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
    return{
      isliked : false,
    }
  },

  props: {
    review : {
      type : Object,
    }
  },

  methods: {
    like() {

    },
    getUserEmail() {
      return sessionStorage.getItem('email')
    }
  },

  computed: {
    computedReview: function(){
      const loginUser = sessionStorage.getItem('email')
      const idx = this.Review.like_user.findIndex( user => {
        return user == loginUser
      })
      this.isliked = idx >= 0 ? true : false 
      
      return this.Review
    }
  }
}
</script>

<style scoped>

</style>