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
          
         <Repository :repos="repositories[0]" padless></Repository>
      </v-card-text>
    </v-card>
  </v-footer>
</template>
<script>
import Repository from "@/components/Repository";
import GitlabService from "@/services/GitlabService";
export default {
  name: "Footer",
  data() {
    return {
      repositories: []
    };
  },
  mounted() {
    this.getGitlabRepos("ahnjiyeon");
  },
  components: {
    Repository
  },
  methods: {
    team() {
      this.$router.push("/team");
    },
    async getGitlabRepos(userName) {
      const response = await GitlabService.getRepos(userName);
      if (response.status !== 200) {
        return;
      }

      this.repositories = response.data;
    }
  }
};
</script>


