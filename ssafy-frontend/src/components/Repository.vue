<template>
  <div class="py-3">
    <v-layout>

      <v-flex xs12>
        <h3 class="font-weight-regular"><a :href="repos.web_url" target="_blank" style="color:dimgray;"> {{repos.path_with_namespace}}</a></h3>
        <p class="subheading mb-1 grey--text text--darken-1 font-weight-light">{{repos.namespace.name}}</p>
      </v-flex>

    </v-layout>
  </div>
</template>

<script>
import GitlabService from '@/services/GitlabService'

export default {
	name: 'Repository',
	props: {
		repos: {type: null}
	},
	data() {
		return {
			stats: {}
		}
	},
  mounted() {
		this.drawStatGraph()
  },
	methods: {
		async drawStatGraph() {
			this.commits = await GitlabService.getCommits(this.repos.id)
		}
	}
}
</script>

<style scoped>
h3{
 
  white-space: nowrap;  

  overflow-x: auto;
  
  overflow-y: hidden;
 
  
}
::-webkit-scrollbar{height: 5px;}
::-webkit-scrollbar-track {background-color:#f1f1f1;}
::-webkit-scrollbar-thumb {background-color:lightgray;border-radius: 10px;}
::-webkit-scrollbar-thumb:hover {background: dimgray;}

</style>
