var path = require("path");

module.exports = {
  entry: "./src/app.jsx",
  devtool: "sourcemaps",
  cache: true,
  debug: true,
  output: {
    //打包输出出bundle.js文件，这个文件就可以导入HTML中了
    path: __dirname,
    filename: "../static/bundle.js"
  },
  module: {
    loaders: [
      {
        test: path.join(__dirname, "."),
        exclude: /(node_modules)/,
        loader: "babel", //用babel转换JSX
        query: {
          cacheDirectory: true,
          presets: ["es2015", "react"]
        }
      }
    ]
  }
};
