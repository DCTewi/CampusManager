using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace CampusManager.Database.Models
{
    public class Course
    {
        public int Id { get; set; }

        [Required, MaxLength(50)]
        public string CourseName { get; set; }

        public string Classroom { get; set; }

        public string CourseTime { get; set; }

        public int TeacherId { get; set; }

        public Teacher Teacher { get; set; }

        public List<StudentCourse> StudentCourses { get; set; }
    }
}
