import request from '@/utils/request'

// 查询所有培养方案
export function listAllTrainPrograms(query) {
  return request({
    url: '/oversea/trainProgram/listAll',
    method: 'get',
    params: query
  })
}

// 根据ID查询培养方案
export function getTrainProgramById(id) {
  return request({
    url: '/oversea/trainProgram/detail',
    method: 'get',
    params: { id }
  })
}

// 根据专业ID查询培养方案
export function getTrainProgramsByMajorId(majorId) {
  return request({
    url: '/oversea/trainProgram/listByMajor',
    method: 'get',
    params: { majorId }
  })
}

// 添加培养方案
export function addTrainProgram(data) {
  return request({
    url: '/oversea/trainProgram/add',
    method: 'post',
    data: data
  })
}

// 更新培养方案
export function updateTrainProgram(data) {
  return request({
    url: '/oversea/trainProgram/update',
    method: 'post',
    data: data
  })
}

// 删除培养方案
export function deleteTrainProgram(id) {
  return request({
    url: '/oversea/trainProgram/delete',
    method: 'get',
    params: { id }
  })
}
