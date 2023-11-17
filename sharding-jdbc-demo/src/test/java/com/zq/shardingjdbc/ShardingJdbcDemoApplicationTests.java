package com.zq.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zq.shardingjdbc.entity.Course;
import com.zq.shardingjdbc.entity.Udict;
import com.zq.shardingjdbc.entity.User;
import com.zq.shardingjdbc.mapper.CourseMapper;
import com.zq.shardingjdbc.mapper.UdictMapper;
import com.zq.shardingjdbc.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingJdbcDemoApplicationTests {

	@Autowired
	private CourseMapper courseMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UdictMapper udictMapper;

	// 测试水平分表
	@Test
	public void addCourse() {
		for (int i = 1; i <= 10; i++) {
			Course course = Course.builder()
					.name("Java")
					.status("INSERT_TEST")
					.userId(Long.parseLong(String.valueOf(i)))
					.build();
			int rows = courseMapper.insert(course);
			System.out.println("rows: " + rows);
		}
	}

	// 测试水平分库分表
	@Test
	public void addCourseDB() {
		for (int i = 1; i <= 10; i++) {
			Course course = Course.builder()
					.name("Java")
					.status("INSERT_TEST")
					.userId(Long.parseLong(String.valueOf(i)))
					.build();
			int rows = courseMapper.insert(course);
			System.out.println("rows: " + rows);
		}
	}

	// 测试水平分库
	@Test
	public void findCourse() {
		LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Course::getCid, 931247192897748993L);
		Course course1 = courseMapper.selectOne(queryWrapper);
		System.out.println("course1: " + course1);

		LambdaQueryWrapper<Course> queryWrapper2 = new LambdaQueryWrapper<>();
		queryWrapper2.eq(Course::getCid, 931247192973246464L);
		Course course2 = courseMapper.selectOne(queryWrapper2);
		System.out.println("course2: " + course2);
	}

	// 测试水平分库分表
	@Test
	public void findCourseDB() {
		LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Course::getCid, 931259009468465153L);
		queryWrapper.eq(Course::getUserId, 3L);

		Course course1 = courseMapper.selectOne(queryWrapper);
		System.out.println("course1: " + course1);
	}

	@Test
	public void addUser() {
		User user = User.builder().name("张三").status("INSERT_TEST").build();
		int rows = userMapper.insert(user);
		System.out.println(rows);
	}

	@Test
	public void findUser() {
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getUserId, 931268569340051457L);

		User user = userMapper.selectOne(queryWrapper);
		System.out.println(user);
	}

	@Test
	public void addUdict() {
		Udict udict = Udict.builder().status("1").value("启用").build();
		int rows = udictMapper.insert(udict);
		System.out.println(rows);
	}

	@Test
	public void deleteUdict() {
		LambdaQueryWrapper<Udict> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Udict::getDictid, 931273077956804609L);
		int rows = udictMapper.delete(queryWrapper);
		System.out.println(rows);
	}
}
