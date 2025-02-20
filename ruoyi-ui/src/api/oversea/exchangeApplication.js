import request from '@/utils/request'

// 查询所有学分置换申请列表
export function listAllExchangeApplications(query) {
  return request({
    url: '/oversea/exchangeApplication/listAll',
    method: 'get',
    params: query
  })
}

// 查询学分置换申请详细信息
export function getExchangeApplicationById(applicationId) {
  return request({
    url: '/oversea/exchangeApplication/detail',
    method: 'get',
    params: { applicationId }
  })
}

// 添加学分置换申请
export function addExchangeApplication(data) {
  return request({
    url: '/oversea/exchangeApplication/add',
    method: 'post',
    data: data
  })
}

// 更新学分置换申请信息
export function updateExchangeApplication(data) {
  return request({
    url: '/oversea/exchangeApplication/update',
    method: 'post',
    data: data
  })
}

// 更新学院审核结果
export function updateCollegeReview(data) {
  return request({
    url: '/oversea/exchangeApplication/update/college',
    method: 'post',
    data: data
  })
}

// 更新学校审核结果
export function updateUniversityReview(data) {
  return request({
    url: '/oversea/exchangeApplication/update/university',
    method: 'post',
    data: data
  })
}

// 删除学分置换申请
export function deleteExchangeApplication(applicationId) {
  return request({
    url: '/oversea/exchangeApplication/delete',
    method: 'get',
    params: { applicationId }
  })
}

// 根据学生ID查询学分置换申请列表
export function listExchangeApplicationsByUserId(userId, query) {
  return request({
    url: '/oversea/exchangeApplication/listByUserId',
    method: 'get',
    params: { userId, ...query }
  })
}
