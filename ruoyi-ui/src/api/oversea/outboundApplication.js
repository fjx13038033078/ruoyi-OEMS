import request from '@/utils/request'

// 查询所有出境申请列表
export function listAllOutboundApplications(query) {
  return request({
    url: '/oversea/outboundApplication/listAll',
    method: 'get',
    params: query
  })
}

// 查询出境申请详细信息
export function getOutboundApplicationById(applicationId) {
  return request({
    url: '/oversea/outboundApplication/detail',
    method: 'get',
    params: { applicationId }
  })
}

// 添加出境申请
export function addOutboundApplication(data) {
  return request({
    url: '/oversea/outboundApplication/add',
    method: 'post',
    data: data
  })
}

// 更新出境申请信息
export function updateOutboundApplication(data) {
  return request({
    url: '/oversea/outboundApplication/update',
    method: 'post',
    data: data
  })
}

// 更新学院审核结果
export function updateCollegeReview(data) {
  return request({
    url: '/oversea/outboundApplication/update/college',
    method: 'post',
    data: data
  })
}

// 更新学校审核结果
export function updateUniversityReview(data) {
  return request({
    url: '/oversea/outboundApplication/update/university',
    method: 'post',
    data: data
  })
}

// 删除出境申请
export function deleteOutboundApplication(applicationId) {
  return request({
    url: '/oversea/outboundApplication/delete',
    method: 'get',
    params: { applicationId }
  })
}

// 根据学生ID查询出境申请列表
export function listOutboundApplicationsByUserId(userId, query) {
  return request({
    url: '/oversea/outboundApplication/listByUserId',
    method: 'get',
    params: { userId, ...query }
  })
}

// 根据审核状态查询出境申请列表
export function listOutboundApplicationsByReviewStatus(collegeReviewResult, universityReviewResult, query) {
  return request({
    url: '/oversea/outboundApplication/listByReviewStatus',
    method: 'get',
    params: { collegeReviewResult, universityReviewResult, ...query }
  })
}
