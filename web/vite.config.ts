import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// https://vitejs.dev/config/
export default defineConfig({
  css: {
    preprocessorOptions: {
      scss: {
        api: "modern-compiler",
      },
    },
  },
  plugins: [vue()],
  server:{
    port: 8046,
    proxy:{
      '/api': {
        target: 'http://localhost:8082',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
      '/photo': {
        target: 'http://localhost:3000',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/photo/, ''),
      }
    }
  },
  // resolve: {
  //   //路劲别名
  //   alias: {
  //     "@": resolve(__dirname, "./src")
  //   }
  // }
});
