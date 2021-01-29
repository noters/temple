// app.js
App({
  globalData: {
    // 服务端地址及端口
    url: 'http://192.168.1.7:8080',
    appid: 'wxb54083b395a5ca53',
    secret: 'ec385cd6dbdd12659664696530775dd0',
    userInfo: null,
    openid: null
  },
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        var that = this
        //var url = 'https://api.weixin.qq.com/sns/jscode2session?appid=' + this.globalData.appid + '&secret=' + this.globalData.secret + '&js_code=' + res.code + '&grant_type=authorization_code';
        var url = this.globalData.url + '/user/login'
        wx.request({
          url: url,
          data: {
            appid: this.globalData.appid,
            secret: this.globalData.secret,
            code: res.code
          },
          method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT  
          // header: {}, // 设置请求的 header  
          success: function (result) {
            console.log(result)
            wx.setStorageSync('user', result.data); //存储openid及session_key
            that.globalData.openid = result.data.openid;
          }
        });
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  }
})
