<template>
  <v-footer dark padless> 
    <v-card flat tile class="lightgray lighten-1 white--text text-center" width="100%">
      <v-card-text></v-card-text>

      <v-card-text class="white--text pt-0">
          본 프로젝트는 단일 페이지 애플리케이션(Single Page Application, SPA)의 개념을 이해하고 반응형 웹(Responsive Web)의 특징을 파악하여 사용자 친화적이고 다양한 디바이스에 최적화 된 웹을 구현하는 것이 목표입니다.
      </v-card-text>

      <v-divider></v-divider>

      <v-card-text class="white--text">
        &copy; {{ new Date().getFullYear() }} —
        <strong>ahnzzi94</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</template>
<script>
export default {
  name: "Footer"
};
</script>

