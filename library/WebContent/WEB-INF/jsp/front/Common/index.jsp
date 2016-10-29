<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

		<div id="search">
			<form action="admin/showAll_Book" method="post">
			<input name="query.column" type="hidden" />
			<select name="query.key" >
				<option value="所有字段" selected="selected">所有字段</option>
				<option value="bookName" >书名</option>
				<option value="author" >作者</option>
				<option value="bookId" >书号</option>
			</select>
			<input name="query.value" />
			<input type="submit" value="检索" />
			</form>
		</div>
		<div id="content">
			<div class="board">
				<div id="announcements">
					<div class="board_title">公告</div>
					<marquee direction='up' scrolldelay='100' scrollamount='2' truespeed="truespeed" 
					onMouseOver='this.stop()' onMouseOut='this.start()' height='200'>
						公告 公告
					</marquee>
				</div>
				<div id="new_comments">
					<div class="board_title">最新书评</div>
					<ul class="board_ul">
					</ul>
				</div>
				<div id="borrow_rank">
					<div class="board_title">借阅排行榜</div>
					<ul class="board_ul">
					</ul>
				</div>
			</div>
			<div class="board">
				<div id="new_books">
					<div class="board_title">新书上架</div>  
					<ul class="board_ul">
					</ul>
				</div>
				<div id="new_books">
					<div class="board_title">文档分类</div>  
					<ul class="board_ul">
					</ul>
				</div>
				<div id="new_books">
					<div class="board_title">用户管理</div>  
					<ul class="board_ul">
					</ul>
				</div>
			</div>
		</div>
