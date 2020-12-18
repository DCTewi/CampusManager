using CampusManager.Database.Models;
using Microsoft.EntityFrameworkCore;

namespace CampusManager.Database
{
    public class CampusContext : DbContext
    {
        public DbSet<Administrator> Administrators { get; set; }

        public DbSet<Teacher> Teachers { get; set; }

        public DbSet<Student> Students { get; set; }

        public DbSet<Course> Courses { get; set; }

        public DbSet<Grade> Grades { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) =>
            optionsBuilder.UseSqlite("Data Source=CampusManager.db");

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<StudentCourse>()
                .HasKey(sc => new { sc.CourseId, sc.StudentId });

            modelBuilder.Entity<StudentCourse>()
                .HasOne(sc => sc.Student)
                .WithMany(s => s.StudentCourses)
                .HasForeignKey(sc => sc.StudentId);

            modelBuilder.Entity<StudentCourse>()
               .HasOne(sc => sc.Course)
               .WithMany(c => c.StudentCourses)
               .HasForeignKey(sc => sc.CourseId);
        }
    }
}
