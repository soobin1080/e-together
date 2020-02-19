<template>
  <div>
    <v-layout>
      <v-flex xs12>
        <p class="font-weight-regular">          
		  Git Link : 

        </p>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import GitlabService from "@/services/GitlabService";

export default {
  name: "Repository",
  props: {
    repos: { type: null }
  },
  data() {
    return {
      stats: {}
    };
  },
  // mounted() {
  //   this.drawStatGraph();
  // },
  // methods: {
  //   async drawStatGraph() {
  //     this.commits = await GitlabService.getCommits(this.repos.id);
  //   }
  // }
};
</script>

<style scoped>
p {
  white-space: nowrap;

  overflow-x: auto;

  overflow-y: hidden;
}
::-webkit-scrollbar {
  height: 5px;
}
::-webkit-scrollbar-track {
  background-color: #f1f1f1;
}
::-webkit-scrollbar-thumb {
  background-color: lightgray;
  border-radius: 10px;
}
::-webkit-scrollbar-thumb:hover {
  background: dimgray;
}
</style>
