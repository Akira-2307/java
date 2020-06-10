package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.board;

public class dao {
	//①DBアクセスに必要な情報の定数を定義

			//接続先DBのURL(jdbc:mysql://[ホスト名orIPアドレス]:[ポート番号]/[データベース名]?serverTimezone=JST)
			private static final String url = "jdbc:mysql://localhost:3306/board?serverTimezone=JST";
			//ユーザ
			private static final String user = "root";
			//パスワード
			private static final String pw = "tAmAzArAsI2307";

			//1件のみ検索するSELECT文を実行するメソッドのサンプル
			//引数は検索する学生のID
			public static board selectboard(String name){
				//②アクセスに必要な変数の初期化
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try{
					//③JDBCドライバをロードする
					Class.forName("com.mysql.cj.jdbc.Driver");

					//④データベースと接続する(コネクションを取ってくる)
					//第1引数→接続先URL
					//第2引数→ユーザ名
					//第3引数→パスワード
					con = DriverManager.getConnection(url, user, pw);

					//⑤SQL文の元を作成する
					String sql = "SELECT * FROM dao WHERE name = ?";

					//⑥SQLを実行するための準備(構文解析)
					pstmt = con.prepareStatement(sql);

					//⑦プレースホルダに値を設定
					pstmt.setString(1, name);

					//⑧SQLを実行し、DBから結果を受領する
					//※更新系SQLとはメソッドが違うので注意！！更新系はexecuteUpdate()
					rs = pstmt.executeQuery();

					//⑨参照先のカーソルを移動する
					rs.next();

					//⑩ResultSetからデータを取り出す
					//get～で取り出す際の型を指定する
					//引数は検索結果のカラム名を指定する(SQLのASで変更している場合は注意)

					String mail =	rs.getString("mail");
					String comment = rs.getString("comment");
					String time =	rs.getString("time");
					String time2 =	rs.getString("time2");

					//⑪返却用Beanのインスタンスを生成しreturnする
					board result = new board(name, mail,comment,time,time2);
					return result;

				}  catch (SQLException e){
					System.out.println("DBアクセスに失敗しました。");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.out.println("DBアクセスに失敗しました。");
					e.printStackTrace();
				} finally {
					//⑫DBとの切断処理
					try {
						//nullかチェックしないとNullPointerExceptionが
						//発生してしまうためチェックしている。
						if( pstmt != null){
							pstmt.close();
						}
					} catch(SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}

					try {
						if( con != null){
							con.close();
						}
					} catch (SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}
				}

				//途中でExceptionが発生した時はnullを返す。
				return null;
			}

			//全件検索するSELECT文を実行するメソッドのサンプル
			public static ArrayList<board> selectAllboard(){
				//アクセスに必要な変数の初期化
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try{
					//JDBCドライバをロードする
					Class.forName("com.mysql.cj.jdbc.Driver");

					//データベースと接続する(コネクションを取ってくる)
					//第1引数→接続先URL
					//第2引数→ユーザ名
					//第3引数→パスワード
					con = DriverManager.getConnection(url, user, pw);

					//SQL文の元を作成する
					String sql = "SELECT * FROM dao";

					//SQLを実行するための準備(構文解析)
					pstmt = con.prepareStatement(sql);

					//SQLを実行し、DBから結果を受領する
					rs = pstmt.executeQuery();

					//return用のArrayList生成
					ArrayList<board> list = new ArrayList<board>();

					//next()の戻り値がfalseになるまでResultSetから
					//データを取得してArrayListに追加していく
					while( rs.next() ) {
						String name = rs.getString("name");
						String mail = rs.getString("mail");
						String comment = rs.getString("comment");
						String time = rs.getString("time");
						String time2 = rs.getString("time2");
						board result = new board(name, mail,comment,time,time2);
						list.add(result);
					}

					//中身の詰まったArrayListを返却する
					return list;

				}  catch (SQLException e){
					System.out.println("DBアクセスに失敗しました。");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.out.println("DBアクセスに失敗しました。");
					e.printStackTrace();
				} finally {
					//⑫DBとの切断処理
					try {
						//nullかチェックしないとNullPointerExceptionが
						//発生してしまうためチェックしている。
						if( pstmt != null){
							pstmt.close();
						}
					} catch(SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}

					try {
						if( con != null){
							con.close();
						}
					} catch (SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}
				}

				//途中でExceptionが発生した時はnullを返す。
				return null;
			}

			//INSERT文を実行するメソッドのサンプル
			//引数は登録したい情報が格納されたBean
			public static void insertboard(board s){
				//②アクセスに必要な変数の初期化
				Connection con = null;
				PreparedStatement pstmt = null;


				try{
					//③JDBCドライバをロードする
					Class.forName("com.mysql.cj.jdbc.Driver");

					//④データベースと接続する(コネクションを取ってくる)
					//第1引数→接続先URL
					//第2引数→ユーザ名
					//第3引数→パスワード
					con = DriverManager.getConnection(url, user, pw);

					//⑤SQL文の元を作成する
					//?をプレースホルダと言います。
					//後の手順で?に値を設定します。
					String sql = "INSERT INTO dao(name, mail, comment,time) VALUES(?,?,?,now())";

					//⑥SQLを実行するための準備(構文解析)
					pstmt = con.prepareStatement(sql);

					//⑦プレースホルダに値を設定
					//第1引数→何番目の?に設定するか(1から数える)
					//第2引数→?に設定する値
					pstmt.setString(1, s.getName());
					pstmt.setString(2, s.getMail());
					pstmt.setString(3, s.getComment());



					//⑧SQLを実行し、DBから結果を受領する
					int result = pstmt.executeUpdate();
					System.out.println(result + "件登録されました。");

					//おまけ：⑥の準備が完了すれば?の値を更新して
					//続けてINSERTすることができる。


				}  catch (SQLException e){
					System.out.println("DBアクセスに失敗しました。");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.out.println("DBアクセスに失敗しました。");
					e.printStackTrace();
				} finally {
					//⑨DBとの切断処理
					try {
						//nullかチェックしないとNullPointerExceptionが
						//発生してしまうためチェックしている。
						if( pstmt != null){
							pstmt.close();
						}
					} catch(SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}

					try {
						if( con != null){
							con.close();
						}
					} catch (SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}
				}
			}

			//DELETE文を実行するメソッドのサンプル
			//引数は削除する学生のID
			public static void deleteboard(String name){
				//②アクセスに必要な変数の初期化
				Connection con = null;
				PreparedStatement pstmt = null;


				try{
					//③JDBCドライバをロードする
					Class.forName("com.mysql.cj.jdbc.Driver");

					//④データベースと接続する(コネクションを取ってくる)
					//第1引数→接続先URL
					//第2引数→ユーザ名
					//第3引数→パスワード
					con = DriverManager.getConnection(url, user, pw);

					//⑤SQL文の元を作成する
					//?をプレースホルダと言います。
					//後の手順で?に値を設定します。
					String sql = "DELETE FROM dao WHERE name = ?";

					//⑥SQLを実行するための準備(構文解析)
					pstmt = con.prepareStatement(sql);

					//⑦プレースホルダに値を設定
					//第1引数→何番目の?に設定するか(1から数える)
					//第2引数→?に設定する値
					pstmt.setString(1, name);

					//⑧SQLを実行し、DBから結果を受領する
					int result = pstmt.executeUpdate();
					System.out.println(result + "件削除されました。");

				}  catch (SQLException e){
					System.out.println("DBアクセスに失敗しました。");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.out.println("DBアクセスに失敗しました。");
					e.printStackTrace();
				} finally {
					//⑨DBとの切断処理
					try {
						//nullかチェックしないとNullPointerExceptionが
						//発生してしまうためチェックしている。
						if( pstmt != null){
							pstmt.close();
						}
					} catch(SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}

					try {
						if( con != null){
							con.close();
						}
					} catch (SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}
				}
			}

			//UPDATE文を実行するメソッドのサンプル
			//引数は変更する学生のBean
			public static ArrayList<board> update(String name){
				//②アクセスに必要な変数の初期化
				Connection con = null;
				PreparedStatement pstmt = null;
				int t = 0;
				ArrayList<board> result = new ArrayList<board>();

				try{
					//③JDBCドライバをロードする
					Class.forName("com.mysql.cj.jdbc.Driver");

					//④データベースと接続する(コネクションを取ってくる)
					//第1引数→接続先URL
					//第2引数→ユーザ名
					//第3引数→パスワード
					con = DriverManager.getConnection(url, user, pw);

					//⑤SQL文の元を作成する
					//?をプレースホルダと言います。
					//後の手順で?に値を設定します。
					String sql = "UPDATE dao SET time2 = NOW()  where name = ?";

					//⑥SQLを実行するための準備(構文解析)
					pstmt = con.prepareStatement(sql);

					//⑦プレースホルダに値を設定
					//第1引数→何番目の?に設定するか(1から数える)
					//第2引数→?に設定する値
					pstmt.setString(1, name);

					//⑧SQLを実行し、DBから結果を受領する
					t = pstmt.executeUpdate();
					System.out.println(t + "件更新されました。");

				}  catch (SQLException e){
					System.out.println("DBアクセスに失敗しました。");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.out.println("DBアクセスに失敗しました。");
					e.printStackTrace();
				} finally {
					//⑨DBとの切断処理
					try {
						//nullかチェックしないとNullPointerExceptionが
						//発生してしまうためチェックしている。
						if( pstmt != null){
							pstmt.close();
						}
					} catch(SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}

					try {
						if( con != null){
							con.close();
						}
					} catch (SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}
				}
				return result;
			}
			public static ArrayList<board> returnbook(String id){
				//②アクセスに必要な変数の初期化
				Connection con = null;
				PreparedStatement pstmt = null;
				int t = 0;
				ArrayList<board> result = new ArrayList<board>();

				try{
					//③JDBCドライバをロードする
					Class.forName("com.mysql.cj.jdbc.Driver");

					//④データベースと接続する(コネクションを取ってくる)
					//第1引数→接続先URL
					//第2引数→ユーザ名
					//第3引数→パスワード
					con = DriverManager.getConnection(url, user, pw);

					//⑤SQL文の元を作成する
					//?をプレースホルダと言います。
					//後の手順で?に値を設定します。
					String sql = "UPDATE book SET lental = '貸出可' where id = ? ";

					//⑥SQLを実行するための準備(構文解析)
					pstmt = con.prepareStatement(sql);

					//⑦プレースホルダに値を設定
					//第1引数→何番目の?に設定するか(1から数える)
					//第2引数→?に設定する値
					pstmt.setString(1, id);

					//⑧SQLを実行し、DBから結果を受領する
					t = pstmt.executeUpdate();
					System.out.println(t + "件更新されました。");

				}  catch (SQLException e){
					System.out.println("DBアクセスに失敗しました。");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.out.println("DBアクセスに失敗しました。");
					e.printStackTrace();
				} finally {
					//⑨DBとの切断処理
					try {
						//nullかチェックしないとNullPointerExceptionが
						//発生してしまうためチェックしている。
						if( pstmt != null){
							pstmt.close();
						}
					} catch(SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}

					try {
						if( con != null){
							con.close();
						}
					} catch (SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}
				}
				return result;
			}
		}