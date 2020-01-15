<template>
  <div>
    <ImgBanner>
      <div class="text-center text-white" style="line-height:1.2em;font-size:1.2em;" slot="text" v-resize-text>Portfolio</div>
    </ImgBanner>
    <v-container>

      <!-- Portfolio -->
      <v-layout>
        <v-flex xs12>
          <PortfolioList :limits="4" :load-more="true"></PortfolioList>
        </v-flex>
      </v-layout>

    </v-container>
  </div>
</template>

<script>
import ImgBanner from '../components/ImgBanner'
import PortfolioList from '../components/PortfolioList'
import ResizeText from "vue-resize-text";

export default {
	name: 'PortfolioPage',
	components: {
		ImgBanner,
		PortfolioList,
  },
  directives: {
    ResizeText
  }
}
</script>
