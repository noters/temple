// pages/detail.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    that.id = options.id
    this.loadGoods(that.id)
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  loadGoods: function (goodsId) {
    var that = this
    var url = app.globalData.url + '/goodsDetail/list'
    wx.request({
      url: url,
      data: {
        id: goodsId
      },
      method: 'POST',
      success: function (result) {
        console.log(result)
        result.data.image = app.globalData.url + result.data.image
        result.data.goodsDetailList.map(item => {
          item.image = app.globalData.url + item.image
        })
        console.log(result)
        that.setData({
          goods: result.data
        })
      }
    });
  }
})