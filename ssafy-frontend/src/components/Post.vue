<template>
  <v-layout py-4 h-100>
    <v-flex>
      <div class="caption">{{formatedDate}}</div>
      <h2 class="color-333 headline font-weight-light">{{title}}</h2>
      <p class="mb-1 color-666 font-weight-light subheading">{{body}}</p>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
	name: 'Post',
	props: {
		date: {type: Date},
		title: {type: String},
		body: {type: String}
	},
  computed: {
		formatedDate() {
			return `${this.date.getFullYear()}년 ${this.date.getMonth()+1}월 ${this.date.getDate()}일`
    }
  }
}
</script>
<style>
  .color-666 {
    color: #666;
  }
  .color-333 {
    color: #333;
  }
  .h-100 {
    height: 100%;
  }
</style>

<style scoped>
.headline {
  display: inline-block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;  
}
.subheading {
  font-size: 12pt;
  /* 한 줄 자르기 */
  display: inline-block; 
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; /* 여러 줄 자르기 추가 스타일 */
  white-space: normal;
  line-height: 1.2;
  height: 4.6em;
  text-align: left;
  word-wrap: break-word;
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;
}
</style>
