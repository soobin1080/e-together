<template>
  <v-footer dark padless>
    <v-card flat tile class="lightgray lighten-1 white--text text-center" width="100%" style="padding-top:10px">
      <v-card-text class="white--text">
        &copy; {{ new Date().getFullYear()}}
        
          <v-tooltip top>
            <template v-slot:activator="{ on }" style="position:absolute">
              <div class="lightgrey--text" v-on="on">
                <strong @click="team">e-together</strong>
              </div>
            </template>
            <span>About Developer</span>           
          </v-tooltip>    
         
      </v-card-text>
    </v-card>
  </v-footer>
</template>
<script>
export default {
  name: "Footer",
  data() {
    return {
     
    };
  },
  
  components: {
    
  },
  methods: {
    team() {
      this.$router.push("/team");
    },
    
  }
};
</script>


